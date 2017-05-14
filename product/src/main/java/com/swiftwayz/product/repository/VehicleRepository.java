package com.swiftwayz.product.repository;

import com.swiftwayz.domain.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by sydney on 2017/04/14.
 */
@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

    Optional<Vehicle> findOneByRegistrationNumber(String registrationNumber);

}
