package com.swiftwayz.web.rest;

import com.swiftwayz.domain.user.Driver;
import com.swiftwayz.service.driver.DriverService;
import com.swiftwayz.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sydney on 2017/04/19.
 */
@RestController
@RequestMapping("/api")
public class DriverController extends BaseController {

    @Autowired
    private DriverService driverService;


    @PostMapping("/driver")
    public ResponseEntity<?> addDriver(@RequestBody Driver driver){
        try {
            Driver driverSaved = driverService.addDriver(driver);
            return httpOk(driverSaved);
        } catch (Exception ex){
            return httpBadRequest(ex);
        }
    }

    @PutMapping("/driver")
    public ResponseEntity updateDriver(@RequestBody Driver driver){
        try{
            Driver updatedDriver = driverService.updateDriver(driver);
            return httpOk(updatedDriver);
        }catch (Exception ex){
            return httpBadRequest(ex);
        }
    }

    @GetMapping("/driver/{id}")
    public ResponseEntity<?> getDriverById(@PathVariable long id){
        try{
            Driver driver = driverService.findOne(id);
            return httpOk(driver);
        } catch (Exception ex){
            return httpBadRequest(ex);
        }
    }

    @GetMapping("/driver")
    public ResponseEntity<?> getDriverByIdNumber(@RequestParam("idNumber") long idNumber){
        try{
            Driver driver = driverService.findByIdNumber(idNumber);
            return httpOk(driver);
        } catch (Exception ex){
            return httpBadRequest(ex);
        }
    }
}
