package com.swiftwayz.web.rest;

import com.swiftwayz.domain.vehicle.Vehicle;
import com.swiftwayz.service.vehicle.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sydney on 2017/04/16.
 */
@RestController
@RequestMapping("api")
public class VehicleController extends BaseController{

    @Autowired
    private VehicleService vehicleService;


    @PostMapping("/vehicle")
    public ResponseEntity<?> addVehicle(@RequestBody Vehicle vehicle){
        try{
            Vehicle add = vehicleService.add(vehicle);
            return httpOk(add);
        } catch (Exception exception){
            return httpBadRequest(exception);
        }
    }


    @PutMapping("/vehicle")
    public ResponseEntity<?> updateVehicle(@RequestBody Vehicle vehicle){
        try{
            Vehicle updatedVehicle = vehicleService.updateVehicle(vehicle);
            return httpOk(updatedVehicle);
        }catch (Exception ex){
            return httpBadRequest(ex.getMessage());
        }
    }

    @DeleteMapping("/vehicle/{id}")
    public ResponseEntity<?> deleteVehicle(@PathVariable Long id){
        try {
            vehicleService.remove(id);
            return httpOk();
        } catch (Exception ex){
            return httpBadRequest("Error deleting vehicle.");
        }
    }
}
