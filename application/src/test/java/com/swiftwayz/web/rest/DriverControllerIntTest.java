package com.swiftwayz.web.rest;

import com.swiftwayz.GoSwiftApplication;
import com.swiftwayz.domain.user.DriverDetail;
import com.swiftwayz.domain.user.User;
import com.swiftwayz.domain.user.VehicleOwner;
import com.swiftwayz.domain.util.Status;
import com.swiftwayz.domain.vehicle.Product;
import com.swiftwayz.domain.vehicle.Vehicle;
import com.swiftwayz.service.driver.DriverService;
import com.swiftwayz.service.product.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.util.Date;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;

/**
 * Created by sydney on 2017/04/19.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoSwiftApplication.class)
@ActiveProfiles("dev")
@Transactional
public class DriverControllerIntTest {

    @Autowired
    private DriverService driverService;

    @Mock
    private ProductService productServiceMock;

    private MockMvc restMvc;

    @Before
    public void setup(){
        DriverController controller = new DriverController();
        ReflectionTestUtils.setField(controller, "driverService", driverService);

        restMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void should_add_driver() throws Exception {

        restMvc.perform(
                post("/api/driver")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(getDriverDetails())))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").isNotEmpty());
    }

    private DriverDetail getDriverDetails() {
        DriverDetail driver = new DriverDetail();

        User user = createUser();

        driver.setUser(user);
        driver.setCrimeCheck("Yes");
        driver.setDateLicenseObtained(new Date());
        driver.setLicenseNumber(123456L);
        driver.setPermitNumber("120FJ5");

        VehicleOwner owner = getVehicleOwner(user);
        driver.setVehicleOwner(owner);
        Vehicle vehicle = getVehicle();
        driver.setVehicle(vehicle);

        return driver;
    }

    private User createUser() {
        User user = new User();
        String firstName = "Sydney";

        user.setFirstName(firstName);
        user.setLastName("Chauke");
        user.setEmail("sm@gamil.com");
        user.setIdNumber(1234567891234L);
        user.setStatus(Status.ACTIVE.getName());
        user.setCellNumber("+27721234567");
        return user;
    }


    private Vehicle getVehicle() {
        Vehicle vehicle = new Vehicle();

        vehicle.setMake("BMW");
        vehicle.setModel("330");
        vehicle.setColor("Black");
        vehicle.setClockMileage(12000);
        vehicle.setYearRegistered(2015);
        vehicle.setSeatCapacity(4);
        vehicle.setVinNumber("VIN320884");
        vehicle.setProduct(getProduct());

        return vehicle;
    }

    private Product getProduct() {
        Product product = new Product();
        product.setCode("goX");
        return product;
    }

    private VehicleOwner getVehicleOwner(User user) {
        VehicleOwner owner = new VehicleOwner();
        owner.setUser(user);
        owner.setDriver('N');
        return owner;
    }
}
