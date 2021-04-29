package com.atguigu.sptingcloud.filter;

import lombok.extern.slf4j.Slf4j;
import org.springframework.cloud.gateway.filter.GatewayFilterChain;
import org.springframework.cloud.gateway.filter.GlobalFilter;
import org.springframework.core.Ordered;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Component;
import org.springframework.web.server.ServerWebExchange;
import reactor.core.publisher.Mono;

import java.util.Date;

@Component
@Slf4j
public class MyLogGateWayFilter implements GlobalFilter, Ordered {
    @Override
    public Mono<Void> filter(ServerWebExchange exchange, GatewayFilterChain chain) {
        String uname = exchange.getRequest().getQueryParams().getFirst("uname");
        log.info("*********************"+new Date()+"**********");
        if (null == uname ){
            exchange.getResponse().setStatusCode(HttpStatus.NOT_ACCEPTABLE);
            log.info("用户名为null，禁止访问");
            return exchange.getResponse().setComplete();
        }
        return chain.filter(exchange);
    }

    /*
    拦截器优先级
     */
    @Override
    public int getOrder() {
        return 0;
    }
}
