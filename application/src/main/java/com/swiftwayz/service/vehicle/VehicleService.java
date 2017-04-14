package com.swiftwayz.service.vehicle;

import com.swiftwayz.domain.vehicle.Vehicle;
import com.swiftwayz.repository.VehicleRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by sydney on 2017/04/08.
 */
@Component
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    public Vehicle add(Vehicle vehicle){
        return  vehicleRepository.save(vehicle);
    }
}
