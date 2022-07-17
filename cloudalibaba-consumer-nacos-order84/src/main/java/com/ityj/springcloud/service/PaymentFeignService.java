package com.ityj.springcloud.service;

import com.ityj.springcloud.entity.model.CommonResult;
import com.ityj.springcloud.service.impl.PaymentFeignServiceFallback;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;

@FeignClient(value = "nacos-payment-provider", fallback = PaymentFeignServiceFallback.class)
public interface PaymentFeignService {

    @GetMapping("/payment/get/{id}")
    CommonResult<String> getPaymentInfo(@PathVariable("id") Integer id);

}
