package com.atguigu.springcloud.service;

import ch.qos.logback.core.util.TimeUtil;
import cn.hutool.core.util.IdUtil;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixProperty;
import org.springframework.stereotype.Service;
import org.springframework.web.bind.annotation.PathVariable;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentService {
    /*
    正常访问，肯定OK
     */
    public String paymentInfo_Ok(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + "  paymentInfo_Ok" + "\t" + "id: " + id + "O(∩_∩)O哈哈~";
    }

    @HystrixCommand(fallbackMethod = "paymentInfo_TimeOutHandler", commandProperties = {
            @HystrixProperty(name = "execution.isolation.thread.timeoutInMilliseconds", value = "5000")
    })
    public String paymentInfo_TimeOut(Integer id) {
        int timenum = 3;
        try {
            TimeUnit.SECONDS.sleep(timenum);
        } catch (InterruptedException e) {
            e.printStackTrace();
        }
        return "线程池： " + Thread.currentThread().getName() + "  paymentInfo_TimeOut" + "\t" + "id: " + id + "O(∩_∩)O哈哈~" + "耗时" + timenum + "秒";
    }

    public String paymentInfo_TimeOutHandler(Integer id) {
        return "线程池： " + Thread.currentThread().getName() + "  paymentInfo_TimeOutHandler" + "\t" + "id: " + id + "\t" + "😋";
    }

    //********************服务熔断***********************************

    @HystrixCommand(fallbackMethod = "paymentCircuitBreacker_fallback",commandProperties = {
            @HystrixProperty(name = "circuitBreaker.enabled",value = "true"),//是否开启断路器
            @HystrixProperty(name = "circuitBreaker.requestVolumeThreshold",value = "10"),//请求次数超过了峰值，熔断器将从关闭状态变为打开状态
            @HystrixProperty(name = "circuitBreaker.sleepWindowInMilliseconds",value = "10000"),//时间窗口期
            @HystrixProperty(name = "circuitBreaker.errorThresholdPercentage",value = "60")//失败率达到多少后跳闸

    })
    public String paymentCircuitBreacker(@PathVariable("id")Integer id){
        if (id<0){
            throw new RuntimeException("***********ID不能为负数***********");
        }
        String serialNum = IdUtil.simpleUUID();
        return Thread.currentThread().getName()+"\t"+"调用成功，流水号： "+serialNum;
    }

    public String paymentCircuitBreacker_fallback(@PathVariable("id") Integer id){
        return "Id不能为负数，请稍后再试，o(╥﹏╥)o，  id: "+ id;
    }






}
