package com.swiftwayz.billing.service;

import com.swiftwayz.billing.repository.AccountRepository;
import com.swiftwayz.domain.billing.Account;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sydney on 2017/05/07.
 */
@Service
@Transactional
public class AccountService {

    @Autowired
    private AccountRepository accountRepository;

    public Account addAccount(Account account){
        return accountRepository.save(account);
    }
}
