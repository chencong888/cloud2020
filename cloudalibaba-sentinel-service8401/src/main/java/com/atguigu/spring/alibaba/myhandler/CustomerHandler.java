package com.atguigu.spring.alibaba.myhandler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.atguigu.springcloud.entities.CommonResult;


public class CustomerHandler {
    //注意使用自定义处理方法一定要加上static
    public static CommonResult handleException1(BlockException blockException){
        return new CommonResult(3333,blockException.getClass().getCanonicalName()+"\t 服务不可用,handleException1");
    }

    public static CommonResult handleException2(BlockException blockException){
        return new CommonResult(4444,blockException.getClass().getCanonicalName()+"\t 服务不可用,handleException2");
    }
}
