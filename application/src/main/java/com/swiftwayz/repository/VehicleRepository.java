package com.swiftwayz.repository;

import com.swiftwayz.domain.vehicle.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;

/**
 * Created by sydney on 2017/04/14.
 */
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {

}
