package com.ityj.springcloud.controller;

import com.ityj.springcloud.entity.dto.PaymentDTO;
import com.ityj.springcloud.entity.model.CommonResult;
import com.ityj.springcloud.service.PaymentService;
import lombok.SneakyThrows;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.cloud.client.ServiceInstance;
import org.springframework.cloud.client.discovery.DiscoveryClient;
import org.springframework.util.StringUtils;
import org.springframework.web.bind.annotation.*;

import javax.validation.Valid;
import java.util.List;
import java.util.concurrent.TimeUnit;

@Slf4j
@RestController
@RequestMapping("/payment")
public class PaymentController {

    @Autowired
    private PaymentService paymentService;

    @Autowired
    private DiscoveryClient discoveryClient;

    @Value("${server.port}")
    private String serverPort;

    @GetMapping("/get/{id}")
    public CommonResult<PaymentDTO> getById(@PathVariable("id") Long id) {
        PaymentDTO paymentDTO = paymentService.getPaymentById(id);
        return CommonResult.success(paymentDTO, "ServerPort:" + serverPort);
    }

    @PostMapping("/save")
    public CommonResult<String> save(@RequestBody @Valid PaymentDTO paymentDTO) {
        String message = paymentService.save(paymentDTO);
        return StringUtils.hasText(message) ? CommonResult.fail(message) : CommonResult.success("ServerPort:" + serverPort);
    }

    @GetMapping("/discovery")
    public CommonResult<DiscoveryClient> discoveryInfo() {
        discoveryClient.getServices().forEach(log::info);
        List<ServiceInstance> instances = discoveryClient.getInstances("CLOUD-PAYMENT-SERVICE");
        instances.forEach(x -> {
            log.info(x.toString());
            log.info(x.getUri().toString());
        });
        return CommonResult.success(discoveryClient);
    }

    @SneakyThrows
    @GetMapping("/timeout")
    public CommonResult<String> timeout() {
        TimeUnit.MILLISECONDS.sleep(1500);
        return CommonResult.success();
    }

}
