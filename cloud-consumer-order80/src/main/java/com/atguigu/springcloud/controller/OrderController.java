package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.lb.LoadBalancer;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.client.RestTemplate;

import javax.annotation.Resource;
import java.net.URI;
import java.util.List;

@RestController
public class OrderController {
    @Resource
    private RestTemplate restTemplate;


    @Value("${server.port}")
    private  String serverPort;

    @Resource
    private DiscoveryClient discoveryClient;

   @Resource
   private LoadBalancer loadBalancer;

    //public  static  final  String PAYMENT_PATH="httP://localhost:8001";在提供者集群中 这个不要写死
    public  static  final  String PAYMENT_PATH="httP://CLOUD-PAYMENT-SERVICE"; //集群中使用提供者的别名


    @GetMapping(value = "/consumer/payment/add")
    public  CommonResult<Payment> add(Payment payment){
        return restTemplate.postForObject(PAYMENT_PATH+"/payment/add",payment, CommonResult.class);
    }


    @GetMapping(value ="/consumer/payment/get/{id}")
    public  CommonResult<Payment> getPaymentById(@PathVariable("id")Long id){
        return restTemplate.getForObject(PAYMENT_PATH+"/payment/get/"+id,CommonResult.class);
    }
    @GetMapping(value = "/consumer/payment/lb")
    public  String getPaymentLB(){
        List<ServiceInstance> serviceInstances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        if(serviceInstances==null||serviceInstances.size()<=0){
            return null;
        }
        ServiceInstance instance = loadBalancer.instance(serviceInstances);
        URI uri = instance.getUri();
        return restTemplate.getForObject(uri+"/payment/lb",String.class);

    }
}
