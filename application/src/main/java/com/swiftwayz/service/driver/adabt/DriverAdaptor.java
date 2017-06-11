package com.swiftwayz.service.driver.adabt;

import com.swiftwayz.domain.user.Driver;
import com.swiftwayz.domain.user.DriverDetail;
import com.swiftwayz.domain.user.User;
import com.swiftwayz.domain.user.VehicleOwner;
import com.swiftwayz.domain.util.ModelMapper;
import org.apache.commons.lang3.BooleanUtils;

import java.util.Collections;

/**
 * Created by sydney on 2017/04/23.
 */
public class DriverAdaptor {

    public static Driver adapt(DriverDetail driverDetail){
        User user = driverDetail.getUser();
        Driver driver = ModelMapper.getMapper().map(user, Driver.class);
        VehicleOwner vehicleOwner = driverDetail.getVehicleOwner();
        char isDriver = vehicleOwner.isDriver();
        if(BooleanUtils.toBoolean(isDriver)){
            vehicleOwner.setDriverDetails(Collections.emptySet());
        }
        driver.setDriverDetail(driverDetail);
        return driver;
    }

    public static DriverDetail adapt(Driver driver) {
        User user = ModelMapper.getMapper().map(driver, User.class);
        DriverDetail driverDetail = driver.getDriverDetail();
        driverDetail.setUser(user);
        return driverDetail;
    }
}
