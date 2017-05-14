package com.swiftwayz.billing.repository;

import com.swiftwayz.domain.billing.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sydney on 2017/05/07.
 */
@Repository
public interface AccountRepository extends JpaRepository<Account, Long>{

}
