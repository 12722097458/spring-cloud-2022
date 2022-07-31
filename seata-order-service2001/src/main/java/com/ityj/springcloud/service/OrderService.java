package com.ityj.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ityj.springcloud.entity.Order;

public interface OrderService extends IService<Order> {

    boolean saveOrder(Order order);

}
