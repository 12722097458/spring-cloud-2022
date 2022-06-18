package com.ityj.springcloud.service;

import com.ityj.springcloud.entity.dto.PaymentDTO;
import com.ityj.springcloud.entity.model.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.stereotype.Component;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;

import javax.validation.Valid;

@Component
@FeignClient(value = "CLOUD-PAYMENT-SERVICE")
public interface PaymentFeignService {

    @GetMapping("/payment/get/{id}")
    CommonResult<PaymentDTO> getById(@PathVariable("id") Long id);

    @PostMapping("/payment/save")
    CommonResult<String> save(@RequestBody @Valid PaymentDTO paymentDTO);

    @GetMapping("/payment/timeout")
    CommonResult<String> timeout();
}
