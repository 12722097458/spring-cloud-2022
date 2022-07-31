package com.ityj.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ityj.springcloud.entity.Storage;

public interface StorageService extends IService<Storage> {

    boolean decrease(Long productId, Integer count);

}
