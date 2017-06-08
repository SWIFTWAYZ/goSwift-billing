package com.swiftwayz.billing.service.reset;

import com.swiftwayz.domain.user.Driver;
import com.swiftwayz.domain.vehicle.Product;
import com.swiftwayz.web.RESTEnv;
import com.swiftwayz.web.RESTServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by sydney on 2017/05/31.
 */
@Component
public class DriverRestService {

    public static final String URL = "http://%s:%s/swift/api/driver";
    public static final String PRODUCT = "PRODUCT";
    @Autowired
    private RESTEnv restEnv;

    @Autowired
    private RESTServer restServer;

    public Driver getDriver(Driver driver){
        String url = String.format(URL,  restEnv.getHost(), restEnv.getPort());
        return restServer.get(url, Driver.class, driver);
    }
}
