package com.swiftwayz.web.rest;

import com.swiftwayz.domain.user.DriverDetail;
import com.swiftwayz.service.driver.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

/**
 * Created by sydney on 2017/04/19.
 */
@RestController
@RequestMapping("/api")
public class DriverController extends BaseController{

    @Autowired
    private DriverService driverService;


    @PostMapping("/driver")
    public ResponseEntity<?> addDriver(@RequestBody DriverDetail driverDetail){
        try {
            DriverDetail savedDriverDetail = driverService.addDriver(driverDetail);
            return httpOk(savedDriverDetail);
        } catch (Exception ex){
            return httpBadRequest(ex);
        }

    }
}
