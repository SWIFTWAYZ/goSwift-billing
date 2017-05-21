package com.swiftwayz.billing.service.adabt;

import com.swiftwayz.domain.billing.Account;
import com.swiftwayz.domain.billing.BankingTransaction;
import com.swiftwayz.domain.billing.BankingTx;
import com.swiftwayz.domain.util.ModelMapper;

/**
 * Created by sydney on 2017/05/21.
 */
public class TransactionAdaptor {

    public static BankingTransaction adapt(BankingTx bankingTx){
        org.modelmapper.ModelMapper mapper = ModelMapper.getMapper();
        return mapper.map(bankingTx, BankingTransaction.class);
    }


    // TODO User modelMapper
    public static BankingTx adapt(BankingTransaction transaction){
        Account account = transaction.getAccount();
        BankingTx bankingTx = new BankingTx();
        bankingTx.setTransactionId(transaction.getTransactionId());
        bankingTx.setAccountId(account.getAccountId());
        bankingTx.setAmount(transaction.getAmount());
        bankingTx.setDescription(transaction.getDescription());
        bankingTx.setInitiator(transaction.getInitiator());

        return bankingTx;
    }
}
