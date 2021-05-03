package com.atguigu.spring.alibaba.controller;

import cn.hutool.core.thread.ThreadUtil;
import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.BlockingDeque;
import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping(value = "/testA")
    public String testA() {
        return "---------testA()-------";
    }

    @GetMapping(value = "/testB")
    public String testB() throws InterruptedException {
        TimeUnit.MICROSECONDS.sleep(300);
        log.info("**********testB********");
        return "---------testB()-------";
    }


    @GetMapping(value = "/hotKey")
    @SentinelResource(value = "hotKey",blockHandler = "hotKeyError")
    public String hotKey(@RequestParam(value = "p1", required = false) String p1,
                         @RequestParam(value = "p2", required = false) String p2) {
        return "**********hotKey*******";
    }


    public String hotKeyError(String p1, String p2, BlockException blockException) {
        return "**********hotKeyError*******";
    }
}
