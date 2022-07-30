package com.ityj.springcloud.service.impl;

import com.ityj.springcloud.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import org.springframework.stereotype.Service;

@Service
public class OrderServiceImpl implements OrderService {

    @GlobalTransactional
    @Override
    public boolean save() {
        return false;
    }
}
