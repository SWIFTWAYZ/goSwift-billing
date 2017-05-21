package com.swiftwayz.billing.service;

import com.swiftwayz.billing.repository.BankingTxRepository;
import com.swiftwayz.billing.service.adabt.TransactionAdaptor;
import com.swiftwayz.domain.billing.Account;
import com.swiftwayz.domain.billing.BankingTransaction;
import com.swiftwayz.domain.billing.BankingTx;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

/**
 * Created by sydney on 2017/05/21.
 */
@Service
public class BankingTxService {

    @Autowired
    private BankingTxRepository bankingTxRepository;

    @Autowired
    private AccountService accountService;

    public BankingTx addTransaction(BankingTx transaction){
        BankingTransaction bankingTransaction = TransactionAdaptor.adapt(transaction);
        Account account = accountService.findAccount(transaction.getAccountId());
        bankingTransaction.setAccount(account);
        BankingTransaction saved = bankingTxRepository.save(bankingTransaction);
        return TransactionAdaptor.adapt(saved);
    }
}
