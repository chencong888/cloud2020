package com.atguigu.springcloud.servier.impl;

import com.atguigu.springcloud.servier.IMessageProvider;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Qualifier;
import org.springframework.cloud.stream.annotation.EnableBinding;
import org.springframework.cloud.stream.messaging.Source;
import org.springframework.messaging.MessageChannel;
import org.springframework.messaging.support.MessageBuilder;

import javax.annotation.Resource;
import java.util.UUID;

@EnableBinding(Source.class)//定义消息的推送管道
@Slf4j
public class MessageProviderImpl implements IMessageProvider {
    @Autowired
    private MessageChannel output; //注意此位置的变量名要与bindings里面的通道名称一致，不然就会报错

    @Override
    public String send() {
        String serial = UUID.randomUUID().toString();
        output.send(MessageBuilder.withPayload(serial).build());
        log.info("****************serial: " + serial);
        return serial;
    }
}
