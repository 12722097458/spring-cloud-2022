package com.ityj.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ityj.springcloud.entity.dto.PaymentDTO;
import com.ityj.springcloud.entity.po.PaymentPO;

public interface PaymentService extends IService<PaymentPO> {
    String save(PaymentDTO paymentDTO);
}
