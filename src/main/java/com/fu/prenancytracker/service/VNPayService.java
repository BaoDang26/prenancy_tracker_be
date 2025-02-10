package com.fu.prenancytracker.service;


import jakarta.servlet.http.HttpServletRequest;

public interface VNPayService {
    String createOrder(int total, String orderInfo, String urlReturn);

    int orderReturn(HttpServletRequest request);
}
