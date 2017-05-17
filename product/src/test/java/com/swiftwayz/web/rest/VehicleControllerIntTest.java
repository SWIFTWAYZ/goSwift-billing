package com.swiftwayz.web.rest;

import com.swiftwayz.ProductApplication;
import com.swiftwayz.domain.vehicle.Product;
import com.swiftwayz.domain.vehicle.Vehicle;
import com.swiftwayz.product.service.VehicleService;
import com.swiftwayz.product.web.rest.VehicleController;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.delete;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by sydney on 2017/04/16.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class)
@ActiveProfiles("dev")
@Transactional
public class VehicleControllerIntTest {

    @Autowired
    private VehicleService vehicleService;

    private MockMvc restMvc;

    private Vehicle addedVehicle;

    @Before
    public void setUp(){

        VehicleController controller = new VehicleController();
        ReflectionTestUtils.setField(controller, "vehicleService", vehicleService);

        restMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void should_add_vehicle() throws Exception {

        Product product = createProduct();
        Vehicle vehicle = createVehicle(product, "Toyota");

        restMvc.perform(
                post("/api/vehicle/")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(vehicle)))
        .andExpect(status().isOk());
    }

    @Test
    public void should_throw_exception_when_add_vehicle_with_incorrect_product() throws Exception {

        Product product = new Product();
        product.setCode("code");
        Vehicle vehicle = createVehicle(product, "Toyota");

        MvcResult mvcResult = restMvc.perform(
                post("/api/vehicle/")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(vehicle)))
                .andExpect(status().isBadRequest())
                .andReturn();

        String error = mvcResult.getResponse().getContentAsString();
        Assertions.assertThat(error).isEqualTo("Product {code} not found.");
    }

    @Test
    public void should_delete_vehicle_by_id() throws Exception {

        restMvc.perform(
                delete("/api/vehicle/1")
                        .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk());
    }

    private Vehicle createVehicle(Product product, String make) {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake(make);
        vehicle.setModel("Corolla");
        vehicle.setYearRegistered(2016);
        vehicle.setColor("white");
        vehicle.setClockMileage(1000);
        vehicle.setDateApproved(new Date());

        vehicle.setProduct(product);
        return vehicle;
    }

    private Product createProduct() {
        Product product = new Product();
        product.setCode("goX");
        return product;
    }
}
