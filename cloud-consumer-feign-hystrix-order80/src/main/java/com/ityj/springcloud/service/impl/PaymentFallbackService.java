package com.ityj.springcloud.service.impl;

import com.ityj.springcloud.entity.model.CommonResult;
import com.ityj.springcloud.service.PaymentFeignService;
import org.springframework.stereotype.Service;

@Service
public class PaymentFallbackService implements PaymentFeignService {
    @Override
    public CommonResult<String> success(Long id) {
        return CommonResult.fail("PaymentFallbackService.success()---> 进入了openfeign默认的兜底策略");
    }

    @Override
    public CommonResult<String> timeout(Long id) {
        return CommonResult.fail("PaymentFallbackService.timeout()---> 进入了openfeign默认的兜底策略");
    }
}
