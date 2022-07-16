package com.ityj.springcloud.handler;

import com.alibaba.csp.sentinel.slots.block.BlockException;
import com.ityj.springcloud.entity.model.CommonResult;

public class CustomerBlockHandler {

    /*
    *  1. 必须是 static
    *   com.alibaba.csp.sentinel.annotation.aspectj.SentinelResourceAspect.invokeResourceWithSentinel
    *  2. 最后一个参数必须是BlockException，并且前面的参数要和原方法保持一致
    * */
    public static CommonResult<String> byResource_blockHandler(BlockException exception) {
        return CommonResult.fail("按资源名称byResource限流测试失败，进入byResource_blockHandler方法！异常为：" + exception.toString());
    }
}
