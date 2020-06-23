package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentService;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
public class PaymentController {
    @Resource
    private PaymentService paymentService;

    @Value("${server.port}")
    private  String serverPort;
    @GetMapping(value = "/payment/hystrix/ok/{id}")
    public  String  paymentOk(@PathVariable("id")Integer id){
        return  paymentService.paymentInfo_OK(id);
    }

    @GetMapping(value = "/payment/hystrix/timeout/{id}")
    public String paymentTiemout(@PathVariable("id")Integer id){
    return paymentService.paymentTimeout(id);
    }

    @GetMapping(value = "/payment/circuit/{id}")
    public  String paymentCircuitTest(@PathVariable("id") Integer id){
        String result = paymentService.paymentCircuitBreaker(id);
        log.info(result);
        return  result;
    }

}
