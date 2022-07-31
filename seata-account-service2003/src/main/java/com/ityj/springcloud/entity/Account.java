package com.ityj.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName(value = "t_account")
public class Account {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "user_id")
    private Long userId;

    //总额度
    @TableField(value = "total")
    private BigDecimal total;

    //已用余额
    @TableField(value = "used")
    private BigDecimal used;

    //剩余可用额度
    @TableField(value = "residue")
    private BigDecimal residue;
}