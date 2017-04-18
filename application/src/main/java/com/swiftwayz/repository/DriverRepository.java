package com.swiftwayz.repository;

import com.swiftwayz.domain.user.DriverDetail;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by sydney on 2017/04/17.
 */
@Repository
public interface DriverRepository extends JpaRepository<DriverDetail, Long>{

    @Query("FROM DriverDetail d WHERE d.user.idNumber =:idNumber ")
    Optional<DriverDetail> findByIdNumber(@Param("idNumber") Long idNumber);
}
