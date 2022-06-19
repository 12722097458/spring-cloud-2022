package com.ityj.springcloud.service;

import com.ityj.springcloud.entity.model.CommonResult;
import com.ityj.springcloud.service.impl.PaymentFallbackService;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@Component
@FeignClient(value = "cloud-provider-hystrix-payment", fallback = PaymentFallbackService.class)
public interface PaymentFeignService {

    @GetMapping("/payment/success/{id}")
    CommonResult<String> success(@PathVariable("id") Long id);

    @GetMapping("/payment/timeout/{id}")
    CommonResult<String> timeout(@PathVariable("id") Long id);
}