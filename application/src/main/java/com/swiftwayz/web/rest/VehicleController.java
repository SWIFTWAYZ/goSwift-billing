package com.swiftwayz.web.rest;

import com.swiftwayz.domain.vehicle.Vehicle;
import com.swiftwayz.service.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sydney on 2017/04/16.
 */
@RestController
@RequestMapping("api")
public class VehicleController {

    @Autowired
    private VehicleService vehicleService;


    @PostMapping("/vehicle")
    public ResponseEntity<?> addVehicle(@RequestBody Vehicle vehicle){
        try{
            vehicleService.add(vehicle);
            return new ResponseEntity<>(HttpStatus.OK);
        } catch (Exception exception){
            String message = exception.getMessage();
            return new ResponseEntity<>(message, HttpStatus.BAD_REQUEST);
        }
    }

}
