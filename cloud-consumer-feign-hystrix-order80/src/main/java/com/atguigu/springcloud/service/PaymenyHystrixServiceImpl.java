package com.atguigu.springcloud.service;

import org.springframework.stereotype.Component;

@Component
public class PaymenyHystrixServiceImpl implements PaymentHystrixService {
    @Override
    public String paymentInfo_Ok(Integer id) {
        return "*************PaymenyHystrixServiceImpl---->paymentInfo_Ok()----😭";
    }

    @Override
    public String paymentInfo_TimeOut(Integer id) {
        return "*************PaymenyHystrixServiceImpl----paymentInfo_TimeOut()----😭";
    }
}
