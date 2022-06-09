package com.ityj.springcloud.controller;

import com.ityj.springcloud.entity.dto.PaymentDTO;
import com.ityj.springcloud.entity.model.CommonResult;
import com.ityj.springcloud.entity.po.PaymentPO;
import com.ityj.springcloud.service.PaymentService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @GetMapping("/get/{id}")
    public CommonResult<PaymentDTO> getById(@PathVariable("id") Long id) {
        PaymentDTO paymentDTO = paymentService.getPaymentById(id);
        return CommonResult.success(paymentDTO);
    }

    @PostMapping("/save")
    public CommonResult<String> save(@RequestBody @Valid PaymentDTO paymentDTO) {
        String message = paymentService.save(paymentDTO);
        return StringUtils.hasText(message) ? CommonResult.fail(message) : CommonResult.success();
    }

}
