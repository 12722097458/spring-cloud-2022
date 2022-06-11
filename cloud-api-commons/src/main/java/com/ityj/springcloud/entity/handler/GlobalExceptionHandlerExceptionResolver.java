package com.ityj.springcloud.entity.handler;

import com.ityj.springcloud.entity.model.CommonResult;
import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

@RestControllerAdvice
@Slf4j
public class GlobalExceptionHandlerExceptionResolver {

    @ExceptionHandler(value = {Exception.class})
    public CommonResult<String> handleException(Exception e) {
        log.info("Error occurred:", e);
        return CommonResult.fail(e.toString());
    }

}
