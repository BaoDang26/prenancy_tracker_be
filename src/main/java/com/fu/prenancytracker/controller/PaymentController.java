package com.fu.prenancytracker.controller;

import com.fu.prenancytracker.service.VNPayService;
import io.swagger.v3.oas.annotations.tags.Tag;
import jakarta.servlet.http.HttpServletRequest;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.ModelAndView;

@Tag(name = "Payment")
@RestController
@RequestMapping("/api/payments")
public class PaymentController {
    private final VNPayService vnPayService;

    public PaymentController(VNPayService vnPayService) {
        this.vnPayService = vnPayService;
    }

    @PostMapping("/submitOrder")
    public String submidOrder(@RequestParam("amount") int orderTotal,
                              @RequestParam("orderInfo") String orderInfo,
                              HttpServletRequest request) {
        String baseUrl = request.getScheme() + "://" + request.getServerName() + ":" + request.getServerPort();
        return vnPayService.createOrder(orderTotal, orderInfo, baseUrl);
    }

    @GetMapping("/vnpay-payment")
    public ModelAndView GetMapping(HttpServletRequest request, Model model) {
        int paymentStatus = vnPayService.orderReturn(request);

        String orderInfo = request.getParameter("vnp_OrderInfo");
        String paymentTime = request.getParameter("vnp_PayDate");
        String transactionId = request.getParameter("vnp_TransactionNo");
        String totalPrice = request.getParameter("vnp_Amount");

        model.addAttribute("orderId", orderInfo);
        model.addAttribute("totalPrice", totalPrice);
        model.addAttribute("paymentTime", paymentTime);
        model.addAttribute("transactionId", transactionId);

        String frontendUrl = "https://frontend.com/payment-result?status=" + paymentStatus;
        return new ModelAndView("redirect:" + frontendUrl);

//        return paymentStatus == 1 ? "ordersuccess" : "orderfail";
    }
}
