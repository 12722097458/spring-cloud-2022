package com.ityj.springcloud.controller;

import com.ityj.springcloud.entity.model.CommonResult;
import com.ityj.springcloud.service.StorageService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class StorageController {
 
    @Autowired
    private StorageService storageService;
 
    //扣减库存
    @PostMapping("/storage/decrease")
    public CommonResult<String> decrease(@RequestParam("productId") Long productId,
                                         @RequestParam("count") Integer count) {
        storageService.decrease(productId, count);
        return CommonResult.success();
    }
}