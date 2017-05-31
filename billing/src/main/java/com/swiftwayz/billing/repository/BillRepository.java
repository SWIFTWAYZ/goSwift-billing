package com.swiftwayz.billing.repository;

import com.swiftwayz.domain.billing.Bill;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sydney on 2017/05/29.
 */
@Repository
public interface BillRepository extends JpaRepository<Bill, Long>{
}
