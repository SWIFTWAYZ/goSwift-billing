package com.swiftwayz.billing.service;

import com.swiftwayz.BillingApplication;
import com.swiftwayz.domain.billing.Account;
import com.swiftwayz.domain.util.Status;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
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

        Account account = new Account();

        account.setBalance(BigDecimal.ZERO);
        account.setName("goSwift");
        account.setDescription("goSwift");
        account.setType("Swift");
        account.setOpenDate(new Date());
        account.setStatus(Status.ACTIVE);
        account.setUserId(Long.valueOf(1));

        Account saveAccount = accountService.addAccount(account);

        Assertions.assertThat(saveAccount.getAccountId()).isNotZero();

    }

}
