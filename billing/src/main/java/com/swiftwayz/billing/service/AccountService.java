package com.swiftwayz.billing.service;

import com.swiftwayz.billing.repository.AccountRepository;
import com.swiftwayz.billing.repository.AccountRepositoryImpl;
import com.swiftwayz.domain.billing.Account;
import com.swiftwayz.domain.billing.BankingTx;
import org.apache.commons.lang3.BooleanUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.time.ZonedDateTime;

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

    @Autowired
    private BankingTxService bankingTxService;

    public Account addAccount(Account account){
        validateUser(account);
        return accountRepository.save(account);
    }

    public BigDecimal debitAccount(BankingTx bankingTx){
        try {
            Long accountId = bankingTx.getAccountId();
            Account account = findAccount(accountId);

            BigDecimal amount = bankingTx.getAmount();
            BigDecimal balance = account.getBalance().add(amount);
            account.setBalance(balance);

//        bankingTx.setInitiator();
            bankingTx.setTransactionDate(ZonedDateTime.now());
            bankingTx.setBalance(balance);
            bankingTx.setType(BankingTx.DEBIT);
            bankingTx.setDescription(BankingTx.DEBIT);

            updateAccount(account);
            bankingTx.setStatus(BankingTx.Status.SUCCESSFUL.name());
            bankingTxService.addTransaction(bankingTx);
            return balance;

        } catch (Exception ex){
            bankingTx.setStatus(BankingTx.Status.FAIL.name());
            bankingTxService.addTransaction(bankingTx);
            throw new RuntimeException(ex);
        }
    }


    public BigDecimal creditAccount(BankingTx bankingTx){
        try {
            Long accountId = bankingTx.getAccountId();
            Account account = findAccount(accountId);

            BigDecimal amount = bankingTx.getAmount();
            BigDecimal balance = account.getBalance().subtract(amount);
            account.setBalance(balance);

//        bankingTx.setInitiator();
            bankingTx.setTransactionDate(ZonedDateTime.now());
            bankingTx.setBalance(balance);
            bankingTx.setType(BankingTx.CREDIT);
            bankingTx.setDescription(BankingTx.CREDIT);

            updateAccount(account);
            bankingTx.setStatus(BankingTx.Status.SUCCESSFUL.name());
            bankingTxService.addTransaction(bankingTx);
            return balance;

        } catch (Exception ex){
            bankingTx.setStatus(BankingTx.Status.FAIL.name());
            bankingTxService.addTransaction(bankingTx);
            throw new RuntimeException(ex);
        }
    }

    private void updateAccount(Account account) {
        try {
            accountRepository.save(account);
        } catch (Exception ex){
            throw new RuntimeException(ex);
        }
    }

    public Account findAccount(Long accountId) {
        Account account = accountRepository.findOne(accountId);
        account.getTransactions().iterator();
        return account;
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
