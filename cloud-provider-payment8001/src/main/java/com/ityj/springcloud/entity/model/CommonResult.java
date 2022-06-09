package com.ityj.springcloud.entity.model;

import com.ityj.springcloud.utils.CommonConstant;
import lombok.AllArgsConstructor;
import lombok.Data;

import java.io.Serializable;

@Data
@AllArgsConstructor
public class CommonResult<T> implements Serializable {
    private static final Integer SUCCESS_CODE = 0;
    private static final Integer FAIL_CODE = -1;

    private Integer code;
    private String msg;
    private transient T data;

    public static <T> CommonResult<T> success(T data) {
        return new CommonResult<>(SUCCESS_CODE, CommonConstant.SUCCESS_MSG, data);
    }

    public static CommonResult<String> success() {
        return new CommonResult<>(SUCCESS_CODE, CommonConstant.SUCCESS_MSG, null);
    }

    public static <T> CommonResult<T> fail(String message) {
        return new CommonResult<>(FAIL_CODE, message, null);
    }
}
