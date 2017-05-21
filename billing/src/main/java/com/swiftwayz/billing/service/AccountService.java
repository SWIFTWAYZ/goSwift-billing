package com.swiftwayz.billing.service;

import com.swiftwayz.billing.repository.AccountRepository;
import com.swiftwayz.billing.repository.AccountRepositoryImpl;
import com.swiftwayz.domain.billing.Account;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by sydney on 2017/05/07.
 */
@Service
@Transactional
public class AccountService {

    public static final String USER_NOT_FOUND = "User (%s) does not exist.";
    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private AccountRepositoryImpl accountRepositoryImpl;

    public Account addAccount(Account account){
        validateUser(account);
        return accountRepository.save(account);
    }

    public BigDecimal debitAccount(BigDecimal amount, Long accountId){
        Account account = accountRepository.findOne(accountId);
        BigDecimal balance = account.getBalance().add(amount);
        account.setBalance(balance);
        accountRepository.save(account);
        return balance;
    }

    private void validateUser(Account account) {
        Long userId = account.getUserId();
        boolean userExist = accountRepositoryImpl.userExist(userId);
        if(BooleanUtils.isFalse(userExist)){
            String message = String.format(USER_NOT_FOUND, userId);
            throw new RuntimeException(message);
        }
    }
}
