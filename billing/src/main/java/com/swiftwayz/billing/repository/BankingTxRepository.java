package com.swiftwayz.billing.repository;

import com.swiftwayz.domain.billing.BankingTransaction;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sydney on 2017/05/21.
 */
@Repository
public interface BankingTxRepository extends JpaRepository<BankingTransaction, Long>{
}
