package com.swiftwayz.billing.service.adabt;

import com.swiftwayz.domain.billing.Account;
import com.swiftwayz.domain.billing.BankingTransaction;
import com.swiftwayz.domain.billing.BankingTx;
import com.swiftwayz.domain.util.ModelMapper;

/**
 * Created by sydney on 2017/05/21.
 */
public class TransactionAdabter {

    public static BankingTransaction adabt(BankingTx bankingTx){
        org.modelmapper.ModelMapper mapper = ModelMapper.getMapper();
        return mapper.map(bankingTx, BankingTransaction.class);
    }

    public static BankingTx adabt(BankingTransaction transaction){
        org.modelmapper.ModelMapper mapper = ModelMapper.getMapper();
        BankingTx bankingTx = mapper.map(transaction, BankingTx.class);
        Account account = transaction.getAccount();
        bankingTx.setAccountId(account.getAccountId());
        return bankingTx;
    }
}
