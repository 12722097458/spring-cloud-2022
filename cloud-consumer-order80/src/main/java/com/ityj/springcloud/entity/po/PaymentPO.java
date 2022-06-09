package com.ityj.springcloud.entity.po;

import lombok.Data;

import java.io.Serializable;

@Data
public class PaymentPO implements Serializable {
    private Long id;

    private String serial;
}
