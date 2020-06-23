package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.service.PaymentHystrixService;
import com.netflix.hystrix.contrib.javanica.annotation.DefaultProperties;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import javax.annotation.Resource;

@RestController
@Slf4j
@DefaultProperties(defaultFallback = "payment_global_handler")
public class OrderHystrixController {

    @Resource
    private PaymentHystrixService paymentHystrixService;

    @GetMapping(value = "/consumer/payment/hystrix/ok/{id}")
    public  String  paymentOk(@PathVariable("id")Integer id){
        String result = paymentHystrixService.paymentOk(id);
        return  result;
    }

    @GetMapping(value = "/consumer/payment/hystrix/timeout/{id}")
    @HystrixCommand
    public  String  paymentTiemout(@PathVariable("id")Integer id){
        int a=10/0;
        String result = paymentHystrixService.paymentTiemout(id);
        return  result;
    }
    public  String  paymentTiemoutHandler(@PathVariable("id")Integer id){
    return  "我是80消费者，对方支付系统繁忙请10s后重试或自己运行出错请检查自己";
    }
//下面是全局的fallback方法
    public String payment_global_handler(){
        return  "Global 全局服务降级处理方法，";
    }



}
