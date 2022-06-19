package com.ityj.springcloud.service.impl;

import com.ityj.springcloud.service.PaymentService;
import lombok.SneakyThrows;
import org.springframework.stereotype.Service;

import java.util.concurrent.TimeUnit;

@Service
public class PaymentServiceImpl implements PaymentService {

    @Override
    public String success(Long id) {
        return Thread.currentThread().getName() + "-success-" + id;
    }

    @SneakyThrows
    @Override
    public String timeout(Long id) {
        long time = 3;
        TimeUnit.SECONDS.sleep(time);
        return Thread.currentThread().getName() + "-timeout:" + time + "-" + id;
    }
}
