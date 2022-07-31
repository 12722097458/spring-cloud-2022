package com.ityj.springcloud.service;

import com.baomidou.mybatisplus.extension.service.IService;
import com.ityj.springcloud.entity.Account;

import java.math.BigDecimal;

public interface AccountService extends IService<Account> {

    boolean decrease(Long userId, BigDecimal money);

}
