package com.fu.prenancytracker.controller;


import com.fu.prenancytracker.exception.EntityNotFoundException;
import com.fu.prenancytracker.model.SubscriptionPlan;
import com.fu.prenancytracker.model.UserSubscription;
import com.fu.prenancytracker.model.enums.SubscriptionStatus;
import com.fu.prenancytracker.payload.request.CreateSubscriptionRequest;
import com.fu.prenancytracker.payload.response.MessageResponse;
import com.fu.prenancytracker.payload.response.PaymentUrlResponse;
import com.fu.prenancytracker.payload.response.UserSubscriptionResponse;
import com.fu.prenancytracker.security.CustomUserDetails;
import com.fu.prenancytracker.service.SubscriptionPlanService;
import com.fu.prenancytracker.service.UserSubscriptionService;
import com.fu.prenancytracker.service.VNPayService;
import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.media.Content;
import io.swagger.v3.oas.annotations.media.Schema;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

import java.time.Instant;
import java.time.LocalDate;
import java.time.LocalDateTime;
import java.time.ZoneOffset;
import java.time.format.DateTimeFormatter;
import java.util.ArrayList;
import java.util.List;

@Tag(name = "User Subscription")
@RestController
@RequestMapping("api/user-subscription")
public class UserSubscriptionController {

    private final UserSubscriptionService userSubscriptionService;
    private final SubscriptionPlanService subscriptionPlanService;
    private final VNPayService vnPayService;

    public UserSubscriptionController(UserSubscriptionService userSubscriptionService, SubscriptionPlanService subscriptionPlanService, VNPayService vnPayService) {
        this.userSubscriptionService = userSubscriptionService;
        this.subscriptionPlanService = subscriptionPlanService;
        this.vnPayService = vnPayService;
    }


    @Operation(summary = "Create subscription", description = "User create subscription and receive payment url")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Create subscription successfully", content = {
                    @Content(schema = @Schema(implementation = PaymentUrlResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @PostMapping("/create")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> createSubscription(@RequestBody CreateSubscriptionRequest createSubscriptionRequest) {
        // lấy thông tin user đang đăng nhập từ context
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // lấy thông tin plan từ id và kiểm tra xem plan có tồn tại không
        SubscriptionPlan subscriptionPlan = subscriptionPlanService.findById(createSubscriptionRequest.getPlanId())
                .orElseThrow(() -> new EntityNotFoundException("Subscription plan id {"
                        + createSubscriptionRequest.getPlanId() + "} not found"));

        // kiểm tra user đã có subscription chưa
        if (userSubscriptionService.checkUserHasSubscription(userDetails.getUser().getId())) {
            return ResponseEntity.badRequest().body(new MessageResponse("User already has subscription"));
        }

        // generate subscription code
        String subscriptionCode = userSubscriptionService.generateSubscriptionCode();

        // tạo order info cho VNPay
        String orderInfo = "Pay for the package " + subscriptionPlan.getName();

        // tạo subscription cho user
        UserSubscription userSubscription = new UserSubscription();
        userSubscription.setUser(userDetails.getUser());
        userSubscription.setSubscriptionPlan(subscriptionPlan);
        userSubscription.setAmount(createSubscriptionRequest.getAmount());
        userSubscription.setStartDate(LocalDate.now());
        // set end date = start date + duration
        userSubscription.setEndDate(LocalDate.now().plusDays(subscriptionPlan.getDuration()));
        userSubscription.setSubscriptionCode(subscriptionCode);
        // set status = payment pending
        userSubscription.setStatus(SubscriptionStatus.PAYMENT_PENDING.name());
        userSubscription.setCreatedDate(Instant.now());

        userSubscriptionService.save(userSubscription);

        // tạo thông tin thanh toán trên VNPay
        String paymentUrl = vnPayService.createOrder(
                userSubscription.getAmount().intValue(),
                orderInfo, subscriptionCode);

        PaymentUrlResponse paymentUrlResponse = new PaymentUrlResponse(paymentUrl);

        return ResponseEntity.ok(paymentUrlResponse);
    }

    @GetMapping("/vnpay-payment")
    public ModelAndView GetMapping(HttpServletRequest request) {
        // url trả về frontend
        String frontendUrl = "https://frontend.com/payment-result?status=";

        // xử lý kết quả trả về từ VNPay
        int paymentStatus = vnPayService.orderReturn(request);

        // nếu thanh toán thất bại thì redirect về trang payment failed
        // nếu thanh toán thành công thì redirect về trang payment success
        // PaymentStatus = 1: thanh toán thành công
        if (paymentStatus != 1) {
            return new ModelAndView("redirect:" + frontendUrl + SubscriptionStatus.PAYMENT_FAILED);
        }

        // lấy thông tin từ request
        String subscriptionCode = request.getParameter("vnp_TxnRef");
        String transactionNo = request.getParameter("vnp_TransactionNo");
        String bankCode = request.getParameter("vnp_BankCode");

        String strPaymentDate = request.getParameter("vnp_PayDate");
        // Định dạng phù hợp với chuỗi
        DateTimeFormatter formatter = DateTimeFormatter.ofPattern("yyyyMMddHHmmss");

        // Parse thành LocalDateTime
        LocalDateTime localDateTime = LocalDateTime.parse(strPaymentDate, formatter);

        // Chuyển đổi sang Instant (UTC)
        Instant paymentDate = localDateTime.toInstant(ZoneOffset.UTC);

        // lấy thông tin User subscription từ subscription code
        UserSubscription userSubscription = userSubscriptionService.findBySubscriptionCode(subscriptionCode).orElseThrow(
                () -> new RuntimeException("User subscription with code { " + subscriptionCode + "} not found")
        );

        // cập nhật thông tin thanh toán
        userSubscription.setTransactionNo(transactionNo);
        userSubscription.setBankCode(bankCode);
        userSubscription.setPaymentDate(paymentDate);
        userSubscription.setStatus(SubscriptionStatus.PENDING.name());

        // cập nhật subscription vào db
        userSubscriptionService.save(userSubscription);

        return new ModelAndView("redirect:" + frontendUrl + SubscriptionStatus.PAYMENT_SUCCESS);
    }

    @Operation(summary = "User get all user subscription", description = "Get user subscription by user id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get user subscription successfully", content = {
                    @Content(schema = @Schema(implementation = UserSubscriptionResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/get-all")
    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUserSubscriptionByUser() {
        // lấy thông tin user đang đăng nhập từ context
        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // lấy thông tin subscription của user
        Iterable<UserSubscription> userSubscriptions = userSubscriptionService.findByUserId(userDetails.getUser().getId());
        List<UserSubscriptionResponse> responses = new ArrayList<>();
        userSubscriptions.forEach(userSubscription -> responses.add(new UserSubscriptionResponse(userSubscription)));
        return ResponseEntity.ok(responses);
    }

    @Operation(summary = "Get user subscription by id", description = "Get user subscription by id")
    @ApiResponses({
            @ApiResponse(responseCode = "200", description = "Get user subscription successfully", content = {
                    @Content(schema = @Schema(implementation = UserSubscriptionResponse.class), mediaType = "application/json")}),
            @ApiResponse(responseCode = "400", description = "Invalid input data"),
            @ApiResponse(responseCode = "500", description = "Internal server error")
    })
    @GetMapping("/get/{userSubscriptionId}")
//    @PreAuthorize("hasRole('USER')")
    public ResponseEntity<?> getUserSubscriptionById(@PathVariable Integer userSubscriptionId) {
        // lấy thông tin user đang đăng nhập từ context
//        CustomUserDetails userDetails = (CustomUserDetails) SecurityContextHolder.getContext().getAuthentication().getPrincipal();

        // lấy thông tin subscription của user
        UserSubscription userSubscription = userSubscriptionService.findById(userSubscriptionId)
                .orElseThrow(() -> new EntityNotFoundException("User subscription not found"));

        // kiểm tra xem user có quyền xem subscription không
//        if (!userSubscription.getUser().getId().equals(userDetails.getUser().getId())) {
//            return ResponseEntity.badRequest().body("User does not have permission to view this subscription");
//        }

        UserSubscriptionResponse response = new UserSubscriptionResponse(userSubscription);
        return ResponseEntity.ok(response);
    }

    @Operation(summary = "Get all user subscription", description = "Get all user subscription")
    @ApiResponse(responseCode = "200", description = "Get all user subscription successfully", content = {
            @Content(schema = @Schema(implementation = UserSubscriptionResponse.class), mediaType = "application/json")})
    @ApiResponse(responseCode = "500", description = "Internal server error")
    @ApiResponse(responseCode = "404", description = "User subscription not found")
    @GetMapping("/all")
//    @PreAuthorize("hasRole('ADMIN')")
    public ResponseEntity<?> getAllUserSubscription() {
        Iterable<UserSubscription> userSubscriptions = userSubscriptionService.findAll();
        List<UserSubscriptionResponse> responses = new ArrayList<>();
        userSubscriptions.forEach(userSubscription -> responses.add(new UserSubscriptionResponse(userSubscription)));
        return ResponseEntity.ok(responses);
    }

}
