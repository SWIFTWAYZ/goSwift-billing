package com.swiftwayz.web.rest;

import com.swiftwayz.GoSwiftApplication;
import com.swiftwayz.domain.vehicle.Product;
import com.swiftwayz.domain.vehicle.Vehicle;
import com.swiftwayz.service.vehicle.VehicleService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;

import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;

/**
 * Created by sydney on 2017/04/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoSwiftApplication.class)
@ActiveProfiles("dev")
@Transactional
public class VehicleControllerIntTest {

    @Autowired
    private VehicleService vehicleService;

    private MockMvc restMvc;

    @Before
    public void setUp(){

        VehicleController controller = new VehicleController();
        ReflectionTestUtils.setField(controller, "vehicleService", vehicleService);

        restMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void should_add_vehicle() throws Exception {

        Vehicle vehicle = createVehicle();

        restMvc.perform(
                post("/api/vehicle/")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(vehicle)))
        .andExpect(status().isOk());
    }

    private Vehicle createVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("Toyota");
        vehicle.setModel("Corolla");
        vehicle.setYearRegistered(2016);
        vehicle.setColor("white");
        vehicle.setClockMileage(1000);
        vehicle.setDateApproved(new Date());

        vehicle.setProduct(createProduct());
        return vehicle;
    }

    private Product createProduct() {
        Product product = new Product();
        product.setCode("goX");
        return product;
    }
}
