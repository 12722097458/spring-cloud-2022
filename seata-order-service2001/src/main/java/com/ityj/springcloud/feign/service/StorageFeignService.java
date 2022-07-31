package com.ityj.springcloud.feign.service;


import com.ityj.springcloud.entity.model.CommonResult;
import org.springframework.cloud.openfeign.FeignClient;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;

@FeignClient(value = "seata-storage-service")
public interface StorageFeignService {

    @PostMapping(value = "/storage/decrease")
    CommonResult<String> decrease(@RequestParam("productId") Long productId, @RequestParam("count") Integer count);

}
