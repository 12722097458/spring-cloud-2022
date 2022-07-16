package com.ityj.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.ityj.springcloud.entity.model.CommonResult;
import com.ityj.springcloud.handler.CustomerBlockHandler;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class RateLimitController {

    @SentinelResource(value = "byResource",
            blockHandlerClass = CustomerBlockHandler.class,
            blockHandler = "byResource_blockHandler")
    @GetMapping("/byResource")
    public CommonResult<String> byResource() {
        return CommonResult.success("按资源名称byResource限流测试成功！");
    }

}
 
