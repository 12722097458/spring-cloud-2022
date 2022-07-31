package com.ityj.springcloud.controller;

import cn.hutool.core.util.IdUtil;
import com.ityj.springcloud.entity.Order;
import com.ityj.springcloud.entity.model.CommonResult;
import com.ityj.springcloud.service.OrderService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class OrderController{

    @Autowired
    private OrderService orderService;

    @GetMapping("/order/create")
    public CommonResult<String> create(Order order) {
        orderService.saveOrder(order);
        return CommonResult.success();
    }

    // 使用Hutool工具包实现雪花算法生成唯一ID
    public static void main(String[] args) {
        String s = IdUtil.getSnowflake(0, 1).nextIdStr();
        System.out.println("s = " + s);
    }
}