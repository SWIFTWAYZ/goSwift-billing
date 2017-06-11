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

    public static final String URL = "http://%s:%s/swift/api/driver/{id}";
    @Autowired
    private RESTEnv restEnv;

    @Autowired
    private RESTServer restServer;

    public Driver getDriver(Long driverId){
        String url = String.format(URL,  restEnv.getHost(), restEnv.getPort(), driverId);
        return restServer.get(url, Driver.class, driverId);
    }
}
