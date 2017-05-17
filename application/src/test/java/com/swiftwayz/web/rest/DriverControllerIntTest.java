package com.swiftwayz.web.rest;

import com.swiftwayz.GoSwiftApplication;
import com.swiftwayz.domain.user.Driver;
import com.swiftwayz.domain.user.DriverDetail;
import com.swiftwayz.domain.user.User;
import com.swiftwayz.domain.user.VehicleOwner;
import com.swiftwayz.domain.util.Status;
import com.swiftwayz.domain.vehicle.Product;
import com.swiftwayz.domain.vehicle.Vehicle;
import com.swiftwayz.service.driver.DriverService;
import com.swiftwayz.service.rest.VehicleRestService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;

import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

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

    @MockBean
    private VehicleRestService vehicleRestService;

    private MockMvc restMvc;

    @Before
    public void setup(){
        DriverController controller = new DriverController();
        ReflectionTestUtils.setField(controller, "driverService", driverService);

        restMvc = MockMvcBuilders.standaloneSetup(controller).build();
    }

    @Test
    public void should_add_driver() throws Exception {

        Driver driver = createDriver();

        restMvc.perform(
                post("/api/driver")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(driver)))
                .andDo(print())
                .andExpect(status().isOk())
                .andExpect(jsonPath("id").isNotEmpty());
    }

    private Driver createDriver() {
        Driver driver = createUser();

        DriverDetail detail = getDriverDetails();
        driver.setDriverDetail(detail);

        VehicleOwner owner = getVehicleOwner();
        detail.setVehicleOwner(owner);

        Vehicle vehicle = getVehicle();
        detail.setVehicle(vehicle);

        return driver;
    }

    private DriverDetail getDriverDetails() {
        DriverDetail detail = new DriverDetail();
        detail.setCrimeCheck("Yes");
        detail.setDateLicenseObtained(new Date());
        detail.setLicenseNumber(123456L);
        detail.setPermitNumber("120FJ5");
        return detail;
    }

    private Driver createUser() {
        Driver driver = new Driver();
        driver.setFirstName("Sydney");
        driver.setLastName("Chauke");
        driver.setEmail("sm@gamil.com");
        driver.setIdNumber(1234567891234L);
        driver.setStatus(Status.ACTIVE.getName());
        driver.setCellNumber("+27721234567");
        return driver;
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

    private VehicleOwner getVehicleOwner() {
        VehicleOwner owner = new VehicleOwner();
        owner.setUser(createOwner());
        owner.setDriver('Y');
        return owner;
    }

    private User createOwner() {
        User user = new User();
        user.setIdNumber(1234567891234L);
        return user;
    }
}
