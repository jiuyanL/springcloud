package com.atguigu.springcloud.service;

import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

@Service
public class PaymentService {

public  String paymentInfo_OK(Integer id){
    return  "线程池："+ Thread.currentThread().getName()+"paymentInfo_Ok, id:"+id+"\t";
}
    @HystrixCommand(fallbackMethod = "paymentTiemoutHandler",commandProperties = {
            @HystrixProperty(name="execution.isolation.thread.timeoutInMilliseconds",value = "5000")
    })
public  String paymentTimeout(Integer id){
    int timeNumber=3000;
//        int b=10/0;
    try {
        Thread.sleep(timeNumber);
    } catch (InterruptedException e) {
        e.printStackTrace();
    }
    return  "线程池："+ Thread.currentThread().getName()+"paymentInfo_Timeout, id:"+id+"\t"+"耗时";

}
    public  String paymentTiemoutHandler(Integer id){
        return  "线程池："+ Thread.currentThread().getName()+"paymentTiemoutHandler,系统繁忙，请稍后再试 id:"+id+"\t"+"耗时";

    }

    //=服务熔断
    @HystrixCommand(fallbackMethod = "paymentCircuitBreaker_fallback",commandProperties ={
            @HystrixProperty(name="circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name="circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数
            @HystrixProperty(name="circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name="circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少跳闸
    } )
    public  String  paymentCircuitBreaker(@PathVariable("id") Integer id){
        if(id <0){
            throw  new RuntimeException("******id 不能为符数");
        }
        String simpleUUID = IdUtil.simpleUUID();
        return  Thread.currentThread().getName()+"\t 流水号："+simpleUUID;
    }
    public  String  paymentCircuitBreaker_fallback(@PathVariable("id") Integer id){
    return  "id 不能为负数 请稍后再试 id:"+id;
    }
}
