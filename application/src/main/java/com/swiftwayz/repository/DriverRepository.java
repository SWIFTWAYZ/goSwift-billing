package com.swiftwayz.repository;

import com.swiftwayz.domain.user.DriverDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sydney on 2017/04/17.
 */
@Repository
public interface DriverRepository extends JpaRepository<DriverDetail, Long>{
}
