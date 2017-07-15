package com.swiftwayz.service.rest;

import com.swiftwayz.domain.vehicle.Vehicle;
import com.swiftwayz.web.RESTServer;
import com.swiftwayz.web.RESTEnv;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by sydney on 2017/05/15.
 */
@Component
public class VehicleRestService {

    public static final String URL = "http://%s:%s/swift/api/vehicle";
    public static final String VEHICLE = "vehicle";
    @Autowired
    private RESTEnv restEnv;

    @Autowired
    private RESTServer restServer;


    public Vehicle addVehicle(Vehicle vehicle){

        String url = String.format(URL,  restEnv.getHost(), restEnv.getPort(),VEHICLE);

        return restServer.post(url, Vehicle.class, vehicle);
    }

}
