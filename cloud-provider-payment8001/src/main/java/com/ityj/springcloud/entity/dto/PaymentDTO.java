package com.ityj.springcloud.entity.dto;

import lombok.Data;

import javax.validation.constraints.NotEmpty;

@Data
public class PaymentDTO {
    @NotEmpty(message = "The value of serial can not be empty!")
    private String serial;
}
