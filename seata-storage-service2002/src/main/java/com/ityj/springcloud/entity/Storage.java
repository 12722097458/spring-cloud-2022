package com.ityj.springcloud.entity;

import com.baomidou.mybatisplus.annotation.IdType;
import com.baomidou.mybatisplus.annotation.TableField;
import com.baomidou.mybatisplus.annotation.TableId;
import com.baomidou.mybatisplus.annotation.TableName;
import lombok.Data;

@Data
@TableName(value = "t_storage")
public class Storage {

    @TableId(value = "id", type = IdType.AUTO)
    private Long id;

    @TableField(value = "product_id")
    private Long productId;

    //总库存
    @TableField(value = "total")
    private Integer total;

    //已用库存
    @TableField(value = "used")
    private Integer used;

    //剩余库存
    @TableField(value = "residue")
    private Integer residue;
}