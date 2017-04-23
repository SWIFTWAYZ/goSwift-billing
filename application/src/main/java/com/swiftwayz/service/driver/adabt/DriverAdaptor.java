package com.swiftwayz.service.driver.adabt;

import com.swiftwayz.domain.user.Driver;
import com.swiftwayz.domain.user.DriverDetail;
import com.swiftwayz.domain.user.User;
import com.swiftwayz.domain.util.ModelMapper;

/**
 * Created by sydney on 2017/04/23.
 */
public class DriverAdaptor {

    public static Driver adapt(DriverDetail driverDetail){
        User user = driverDetail.getUser();
        Driver driver = ModelMapper.getMapper().map(user, Driver.class);
        driver.setDriverDetail(driverDetail);
        return driver;
    }

}
