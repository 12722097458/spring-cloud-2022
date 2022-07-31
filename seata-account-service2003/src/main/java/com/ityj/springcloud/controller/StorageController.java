package com.ityj.springcloud.controller;

import com.ityj.springcloud.entity.model.CommonResult;
import com.ityj.springcloud.service.AccountService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import java.math.BigDecimal;

@RestController
public class StorageController {
 
    @Autowired
    private AccountService accountService;
 
    //扣减库存
    @PostMapping("/account/decrease")
    public CommonResult<String> decrease(@RequestParam("userId") Long userId,
                                         @RequestParam("money") BigDecimal money) {
        accountService.decrease(userId, money);
        return CommonResult.success();
    }
}