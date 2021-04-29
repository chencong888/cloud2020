package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextconfig {
    @LoadBalanced//使用@LoadBalance注解赋予RestTemplate负载均衡的能力
    @Bean
    public RestTemplate getRestTemplate() {
        return new RestTemplate();
    }
}
