package com.swiftwayz.billing.service;

import com.swiftwayz.BillingApplication;
import com.swiftwayz.domain.billing.BankingTransaction;
import com.swiftwayz.domain.billing.BankingTx;
import org.assertj.core.api.Assertions;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.math.BigDecimal;

/**
 * Created by sydney on 2017/05/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BillingApplication.class)
@ActiveProfiles("dev")
@Transactional
public class BankingTxServiceIntTest {

    @Autowired
    private BankingTxService bankingTxService;

    @Test
    public void should_create_transaction(){
        BankingTx transaction = new BankingTx();
        transaction.setAccountId(1002L);
        transaction.setDescription("Debit");
        transaction.setAmount(BigDecimal.valueOf(50));
        transaction.setInitiator("Sydney");

        BankingTx bankingTx = bankingTxService.addTransaction(transaction);

        Assertions.assertThat(bankingTx.getTransactionId()).isNotZero();

    }
}
