package com.ityj.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ityj.springcloud.entity.Order;
import com.ityj.springcloud.feign.service.AccountFeignService;
import com.ityj.springcloud.feign.service.StorageFeignService;
import com.ityj.springcloud.mapper.OrderMapper;
import com.ityj.springcloud.service.OrderService;
import io.seata.spring.annotation.GlobalTransactional;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class OrderServiceImpl extends ServiceImpl<OrderMapper, Order> implements OrderService {

    @Autowired
    private StorageFeignService storageFeignService;

    @Autowired
    private AccountFeignService accountFeignService;

    @GlobalTransactional(name = "fsp-create-order", rollbackFor = Exception.class)
    @Override
    public boolean saveOrder(Order order) {

        log.info("----->开始新建订单");
        //新建订单
        baseMapper.insert(order);

        //扣减库存
        log.info("----->订单微服务开始调用库存，做扣减Count");
        storageFeignService.decrease(order.getProductId(), order.getCount());
        log.info("----->订单微服务开始调用库存，做扣减end");

        //扣减账户
        log.info("----->订单微服务开始调用账户，做扣减Money");
        accountFeignService.decrease(order.getUserId(), order.getMoney());
        log.info("----->订单微服务开始调用账户，做扣减end");

        //修改订单状态，从零到1代表已经完成
        log.info("----->修改订单状态开始");
        order.setStatus(1);
        baseMapper.updateById(order);
        log.info("----->修改订单状态结束");

        log.info("----->下订单成功！");

        return true;
    }
}
