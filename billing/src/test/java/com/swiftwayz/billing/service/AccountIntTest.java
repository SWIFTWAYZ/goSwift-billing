package com.swiftwayz.billing.service;

import com.swiftwayz.BillingApplication;
import com.swiftwayz.domain.billing.Account;
import com.swiftwayz.domain.util.Status;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cglib.core.internal.LoadingCache;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by sydney on 2017/05/07.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BillingApplication.class)
@ActiveProfiles("dev")
@Transactional
public class AccountIntTest {

    @Autowired
    private AccountService accountService;

    @Test
    public void should_add_account(){
        Account account = createAccount();
        Account saveAccount = accountService.addAccount(account);
        Assertions.assertThat(saveAccount.getAccountId()).isNotZero();
    }

    @Test
    public void should_throw_exception_when_adding_account_for_invalid_user(){
        Account account = createAccount();
        account.setUserId(500L);
        try {
            accountService.addAccount(account);
            Assertions.fail("should throw user not found");
        } catch (Exception e){
            Assertions.assertThat(e).hasMessage("User (500) does not exist.");
        }
    }

    @Test
    public void should_debit_account_by_100_bucks(){
        BigDecimal balance = accountService.debitAccount(BigDecimal.valueOf(100.59), 1002L);
        Assertions.assertThat(balance).isEqualTo(BigDecimal.valueOf(200.59));
    }

    private Account createAccount() {
        Account account = new Account();

        account.setBalance(BigDecimal.ZERO);
        account.setName("goSwift");
        account.setDescription("goSwift");
        account.setType("Swift");
        account.setOpenDate(new Date());
        account.setStatus(Status.ACTIVE.getName());
        account.setUserId(Long.valueOf(1));
        return account;
    }

}
