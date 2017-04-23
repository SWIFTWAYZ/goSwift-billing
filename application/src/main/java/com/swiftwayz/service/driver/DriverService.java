package com.swiftwayz.service.driver;

import com.swiftwayz.domain.user.Driver;
import com.swiftwayz.domain.user.DriverDetail;
import com.swiftwayz.domain.user.User;
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

    public Driver addDriver(DriverDetail driverDetail) {
        User user = driverDetail.getUser();
        Vehicle vehicle = driverDetail.getVehicle();
        Validate.notNull(user, "User is required.");
        Validate.notNull(vehicle, "Vehicle is required.");

        DriverDetail saved = driverRepository.save(driverDetail);

        return DriverAdaptor.adapt(saved);
    }

    public Driver findByIdNumber(Long idNumber) {
        Optional<DriverDetail> driverD = driverRepository.findByIdNumber(idNumber);
        DriverDetail driverDetail = driverD.get();
        Validate.notNull(driverDetail, "Driver not found.");

        return DriverAdaptor.adapt(driverD.get());
    }
}
