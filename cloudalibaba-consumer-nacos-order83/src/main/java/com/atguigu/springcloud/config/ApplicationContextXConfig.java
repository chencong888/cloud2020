package com.atguigu.springcloud.config;

import org.springframework.cloud.client.loadbalancer.LoadBalanced;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.web.client.RestTemplate;

@Configuration
public class ApplicationContextXConfig {
    @Bean
    @LoadBalanced//注意springcloudalibaba-nacos默认集成了ribbon，需添加此注解使用负载均衡
    public RestTemplate restTemplate(){
        return new RestTemplate();
    }
}
