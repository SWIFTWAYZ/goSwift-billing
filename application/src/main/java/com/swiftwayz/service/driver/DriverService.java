package com.swiftwayz.service.driver;

import com.swiftwayz.domain.user.DriverDetail;
import com.swiftwayz.domain.user.User;
import com.swiftwayz.domain.vehicle.Vehicle;
import com.swiftwayz.repository.DriverRepository;
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

    public DriverDetail addDriver(DriverDetail driverDetail) {
        User user = driverDetail.getUser();
        Vehicle vehicle = driverDetail.getVehicle();
        Validate.notNull(user,"User is required.");
        Validate.notNull(vehicle, "Vehicle is required.");

        return driverRepository.save(driverDetail);
    }

    public Optional<DriverDetail> findByIdNumber(Long idNumber) {
        return driverRepository.findByIdNumber(idNumber);
    }
}
