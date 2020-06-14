package com.atguigu.springcloud.controller;

import com.atguigu.springcloud.entities.CommonResult;
import com.atguigu.springcloud.entities.Payment;
import com.atguigu.springcloud.service.Impl.PaymentServiceImp;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.web.bind.annotation.*;

import javax.annotation.Resource;
import java.util.List;

@RestController
@Slf4j
public class PaymentController {
    //你传给前端妹子的应该事json字符串 不用告知你的业务逻辑
    @Resource
    private PaymentServiceImp paymentServiceImp;
    @Value("${server.port}")
    private  String serverPort;
    @Resource
    private DiscoveryClient discoveryClient;
    @PostMapping(value = "/payment/add")
    public CommonResult  add(@RequestBody Payment payment){
        int result = paymentServiceImp.add(payment);
        log.info("插入数据"+result);
        if (result>0){
            //添加成功
            return  new CommonResult(200,"插入成功 serverPort:"+serverPort,result);
        }else {
            //添加失败

            return  new CommonResult(444,"插入失败 ",null);
        }
    }
    @GetMapping(value = "/payment/get/{id}")
    public  CommonResult getPaymentById(@PathVariable("id")Long id){
        Payment payment = paymentServiceImp.getPaymentById(id);
        if (payment!=null){
            //查询成功
            return new CommonResult(200,"查询成功 serverPort:"+serverPort,payment);
        }else {
            //查询失败
            return  new CommonResult(444,"查询失败",null);
        }
    }
    @GetMapping(value = "/payment/discovery")
    public  Object discovery(){
        //这个service集合是eureka中的服务名 CLOUD-ORDER-SERVICE  CLOUD-PAYMENT-SERVICE
        List<String> services = discoveryClient.getServices();
        for (String service : services) {
            log.info(service);
        }

        return this.discoveryClient;
    }

}
