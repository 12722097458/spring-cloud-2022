package com.ityj.springcloud.feign.service;


import com.ityj.springcloud.entity.model.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

import java.math.BigDecimal;

@FeignClient(value = "seata-account-service")
public interface AccountFeignService {

    @PostMapping(value = "/account/decrease")
    CommonResult<String> decrease(@RequestParam("userId") Long userId, @RequestParam("money") BigDecimal money);
}
