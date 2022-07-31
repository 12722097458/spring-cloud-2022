package com.ityj.springcloud.service.impl;

import com.baomidou.mybatisplus.core.conditions.query.QueryWrapper;
import com.baomidou.mybatisplus.extension.service.impl.ServiceImpl;
import com.ityj.springcloud.entity.Account;
import com.ityj.springcloud.mapper.AccountMapper;
import com.ityj.springcloud.service.AccountService;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Service;

import java.math.BigDecimal;

@Service
@Slf4j
public class AccountServiceImpl extends ServiceImpl<AccountMapper, Account> implements AccountService {

    //扣减库存信息
    @Override
    public boolean decrease(Long userId, BigDecimal money) {
        QueryWrapper<Account> queryWrapper = new QueryWrapper<>();
        queryWrapper.eq("user_id", userId);
        Account account = baseMapper.selectOne(queryWrapper);
        if (account == null) {
            log.warn("Current userId:{} doesn't exist in database.", userId);
            throw new RuntimeException("Data error!");
        }

        log.info("------->account-service中扣减账户余额开始");
        account.setUsed(account.getUsed().add(money));
        account.setResidue(account.getResidue().subtract(money));
        int updateCount = baseMapper.updateById(account);
        log.info("------->account-service中扣减账户余额结束");

        return updateCount > 0;
    }
}
