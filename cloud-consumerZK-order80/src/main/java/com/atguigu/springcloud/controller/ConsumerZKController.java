package com.atguigu.springcloud.controller;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;

@RestController
public class ConsumerZKController {

    public  static  final  String INVOKE_PATH="http://cloud-payment-service";
    @Resource
    private RestTemplate restTemplate;
    @GetMapping(value = "/consumer/payment/zk")
    public  String zookeeperTest() {
    return  restTemplate.getForObject(INVOKE_PATH+"/payment/zk",String.class);
    }
    }
