package com.swiftwayz.billing.service;

import com.swiftwayz.BillingApplication;
import com.swiftwayz.domain.billing.Account;
import com.swiftwayz.domain.billing.BankingTx;
import com.swiftwayz.domain.util.Status;
import static org.assertj.core.api.Assertions.*;
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
        Account account = createAccount();
        Account saveAccount = accountService.addAccount(account);
        assertThat(saveAccount.getAccountId()).isNotZero();
    }

    @Test
    public void should_get_account(){
        Account account = accountService.findAccount(1002L);
        assertThat(account.getAccountId()).isNotZero();
        assertThat(account.getTransactions()).isNotNull();
    }

    @Test
    public void should_throw_exception_when_adding_account_for_invalid_user(){
        Account account = createAccount();
        account.setUserId(500L);
        try {
            accountService.addAccount(account);
            fail("should throw user not found");
        } catch (Exception e){
            assertThat(e).hasMessage("User (500) does not exist.");
        }
    }

    @Test
    public void should_debit_account_by_100_bucks(){
        BankingTx bankingTx = new BankingTx();
        bankingTx.setAccountId(1002L);
        bankingTx.setAmount(BigDecimal.valueOf(100.59));
        BigDecimal balance = accountService.debitAccount(bankingTx);

        BigDecimal accountBalance = BigDecimal.valueOf(200.59);
        assertThat(balance).isEqualTo(accountBalance);

        assertThat(bankingTx.getStatus()).isEqualTo(BankingTx.Status.SUCCESSFUL.name());
        assertThat(bankingTx.getBalance()).isEqualTo(accountBalance);
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
