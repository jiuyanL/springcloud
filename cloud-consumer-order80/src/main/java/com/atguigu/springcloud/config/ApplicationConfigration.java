package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationConfigration {
//客户端访问服务器端  需要使用resttemplate
    //方法名其实就是别名
    @Bean
//    @LoadBalanced// 使用@LoadBalanced注解赋予restTemplate负载均衡的能力
    public RestTemplate getRestTemplate(){
        return  new RestTemplate();
    }
}
