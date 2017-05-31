package com.swiftwayz.billing.service.reset;

import com.swiftwayz.web.RESTServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by sydney on 2017/05/31.
 */
@Component
public class ProductService {

    public static final String URL = "http://%s:%s/swift/api/vehicle";
    public static final String VEHICLE = "vehicle";
//    @Autowired
//    private RESTEnv restEnv;

    @Autowired
    private RESTServer restServer;
}
