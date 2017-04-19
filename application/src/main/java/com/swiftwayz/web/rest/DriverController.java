package com.swiftwayz.web.rest;

import com.swiftwayz.service.driver.DriverService;
import org.springframework.beans.factory.annotation.Autowired;
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


}
