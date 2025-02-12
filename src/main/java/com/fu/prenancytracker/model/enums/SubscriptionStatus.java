package com.fu.prenancytracker.model.enums;

public enum SubscriptionStatus {
    PAYMENT_PENDING, // Chờ thanh toán
    PAYMENT_FAILED, // Thanh toán thất bại
    PAYMENT_SUCCESS, // Thanh toán thành công
    PENDING, // Đang sử dụng
    FINISHED, // Đã kết thúc
    CANCELLED // Đã hủy
}
