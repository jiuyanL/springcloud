package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymentFallbackService implements  PaymentHystrixService {
    @Override
    public String paymentOk(Integer id) {
        return "PaymentFallbackService-->paymentOk  服务器宕机 客户端服务降级";
    }

    @Override
    public String paymentTiemout(Integer id) {
        return "PaymentFallbackService-->paymentTiemout服务器宕机 客户端服务降级";
    }
}
