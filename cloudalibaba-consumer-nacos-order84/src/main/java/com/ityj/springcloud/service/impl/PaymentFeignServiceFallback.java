package com.ityj.springcloud.service.impl;

import com.ityj.springcloud.entity.model.CommonResult;
import com.ityj.springcloud.service.PaymentFeignService;
import org.springframework.stereotype.Component;

@Component
public class PaymentFeignServiceFallback implements PaymentFeignService {
    @Override
    public CommonResult<String> getPaymentInfo(Integer id) {
        return CommonResult.fail("进入getPaymentInfo...的兜底方法PaymentFeignServiceFallback.getPaymentInfo()......");
    }
}
