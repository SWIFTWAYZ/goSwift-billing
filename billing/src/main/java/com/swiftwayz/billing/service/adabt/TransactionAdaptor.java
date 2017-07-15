package com.swiftwayz.billing.service.adabt;

import com.swiftwayz.domain.billing.Account;
import com.swiftwayz.domain.billing.BankingTransaction;
import com.swiftwayz.domain.billing.BankingTx;
import com.swiftwayz.domain.util.ModelMapper;
import org.modelmapper.convention.MatchingStrategies;

/**
 * Created by sydney on 2017/05/21.
 */
public class TransactionAdaptor {

    public static BankingTransaction adapt(BankingTx bankingTx){
        org.modelmapper.ModelMapper mapper = ModelMapper.getMapper();
        return mapper.map(bankingTx, BankingTransaction.class);
    }

    public static BankingTx adapt(BankingTransaction transaction){
        org.modelmapper.ModelMapper mapper = ModelMapper.getMapper();
        mapper.getConfiguration().setMatchingStrategy(MatchingStrategies.STRICT);
        BankingTx bankingTx = mapper.map(transaction, BankingTx.class);
        Account account = transaction.getAccount();
        bankingTx.setAccountId(account.getAccountId());
        return bankingTx;
    }
}
