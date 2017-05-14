package com.swiftwayz.service.driver;

import com.swiftwayz.domain.user.Driver;
import com.swiftwayz.domain.user.DriverDetail;
import com.swiftwayz.domain.user.VehicleOwner;
import com.swiftwayz.domain.vehicle.Vehicle;
import com.swiftwayz.repository.DriverRepository;
import com.swiftwayz.service.driver.adabt.DriverAdaptor;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by sydney on 2017/04/17.
 */
@Service
@Transactional
public class DriverService {

    @Autowired
    private DriverRepository driverRepository;
//ToDO remove the dependency of vehicle
//    @Autowired
//    private VehicleService vehicleService;

    public Driver addDriver(Driver driver) {
        DriverDetail driverDetail = DriverAdaptor.adapt(driver);

        Vehicle vehicle = driverDetail.getVehicle();

        validateVehicle(vehicle);

        VehicleOwner vehicleOwner = driverDetail.getVehicleOwner();
        Validate.notNull(vehicleOwner, "Vehicle owner is required.");

        DriverDetail saved = driverRepository.save(driverDetail);
        return DriverAdaptor.adapt(saved);
    }


    public Driver findByIdNumber(Long idNumber) {
        Optional<DriverDetail> driverD = driverRepository.findByIdNumber(idNumber);
        DriverDetail driverDetail = driverD.get();
        Validate.notNull(driverDetail, "Driver not found.");

        return DriverAdaptor.adapt(driverD.get());
    }

    public Driver updateDriver(Driver driver){

        DriverDetail driverDetail = DriverAdaptor.adapt(driver);

        Vehicle vehicle = driverDetail.getVehicle();
        validateVehicle(vehicle);

        VehicleOwner vehicleOwner = driverDetail.getVehicleOwner();
        Validate.notNull(vehicleOwner, "Vehicle owner is required.");

        DriverDetail saved = driverRepository.saveAndFlush(driverDetail);
        return DriverAdaptor.adapt(saved);
    }

    private void validateVehicle(Vehicle vehicle) {
//        Validate.notNull(vehicle, "Vehicle is required.");
//        vehicleService.validateProduct(vehicle);
    }
}
