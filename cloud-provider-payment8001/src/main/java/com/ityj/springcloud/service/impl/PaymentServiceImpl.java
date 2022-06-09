package com.ityj.springcloud.service.impl;

import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ityj.springcloud.entity.dto.PaymentDTO;
import com.ityj.springcloud.entity.po.PaymentPO;
import com.ityj.springcloud.mapper.PaymentMapper;
import com.ityj.springcloud.service.PaymentService;
import org.springframework.beans.BeanUtils;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
public class PaymentServiceImpl extends ServiceImpl<PaymentMapper, PaymentPO> implements PaymentService {

    @Transactional
    @Override
    public String save(PaymentDTO paymentDTO) {
        PaymentPO paymentPO = new PaymentPO();
        BeanUtils.copyProperties(paymentDTO, paymentPO);
        int insert = baseMapper.insert(paymentPO);
        return insert > 0 ? null : "Save error!";
    }
}
