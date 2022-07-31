package com.ityj.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

import java.math.BigDecimal;

@Data
@TableName(value = "t_order")
public class Order {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "user_id")
    private Long userId;

    @TableField(value = "product_id")
    private Long productId;

    @TableField(value = "count")
    private Integer count;

    @TableField(value = "money")
    private BigDecimal money;

    @TableField(value = "status")
    private Integer status; //订单状态：0：创建中；1：已完结
}