package com.ityj.springcloud.controller;

import com.alibaba.csp.sentinel.annotation.SentinelResource;
import com.alibaba.csp.sentinel.slots.block.BlockException;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.util.concurrent.TimeUnit;

@RestController
@Slf4j
public class FlowLimitController {
    @GetMapping("/testA")
    public String testA() {
        return "------testA";
    }

    @GetMapping("/testB")
    public String testB() {
        log.info("Running...testB()");
        return "------testB";
    }

    /*
    *   来测试熔断规则的异常比例和异常数
     * */
    @GetMapping("/testError")
    public String testE(@RequestParam(value = "hasError", defaultValue = "true") boolean hasError) {
        if (hasError) {
            Double.valueOf("sf");
            log.info("Running...testError()");
        }
        return "------testError";
    }

    @GetMapping("/sleep")
    public String sleep() {
        try {
            TimeUnit.MILLISECONDS.sleep(600);
        } catch (InterruptedException e) {
            log.info("Error: ", e);
        }
        log.info("Running...sleep()");
        return "------sleep";
    }

    @SentinelResource(value = "testHotKey", blockHandler = "hotKey_blockHandler")
    @GetMapping("/testHotKey")
    public String testHotKey(@RequestParam(value = "id", required = false) String id,
                             @RequestParam(value = "name", required = false)  String name) {
        return "------testHotKey:" + id + name;
    }

    public String hotKey_blockHandler(String id, String name, BlockException e) {
        return "------hotKey_blockHandler";
    }

}