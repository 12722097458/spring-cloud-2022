package com.ityj.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ityj.springcloud.entity.Storage;
import com.ityj.springcloud.mapper.StorageMapper;
import com.ityj.springcloud.service.StorageService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

@Service
@Slf4j
public class StorageServiceImpl extends ServiceImpl<StorageMapper, Storage> implements StorageService {

    //扣减库存信息
    @Override
    public boolean decrease(Long productId, Integer count) {
        QueryWrapper<Storage> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("product_id", productId);
        Storage storage = baseMapper.selectOne(queryWrapper);
        if (storage == null) {
            log.warn("Current productId:{} doesn't exist in database.", productId);
            throw new RuntimeException("Data error!");
        }

        log.info("------->storage-service中扣减库存开始");
        storage.setUsed(storage.getUsed() + count);
        storage.setResidue(storage.getResidue() - count);
        int updateCount = baseMapper.updateById(storage);
        log.info("------->storage-service中扣减库存结束");

        return updateCount > 0;
    }
}
