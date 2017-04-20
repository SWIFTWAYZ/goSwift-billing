package com.swiftwayz.service.driver;

import com.swiftwayz.GoSwiftApplication;
import com.swiftwayz.domain.user.DriverDetail;
import com.swiftwayz.domain.user.User;
import com.swiftwayz.domain.user.VehicleOwner;
import com.swiftwayz.domain.util.Status;
import static org.assertj.core.api.Assertions.*;

import com.swiftwayz.domain.vehicle.Product;
import com.swiftwayz.domain.vehicle.Vehicle;
import com.swiftwayz.service.vehicle.VehicleService;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.net.IDN;
import java.util.Date;
import java.util.Optional;

/**
 * Created by sydney on 2017/04/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoSwiftApplication.class)
@ActiveProfiles("dev")
@Transactional
public class DriverServiceIntTest {

    public static final Long ID_NUMBER = 1234567899999L;
    @Autowired
    private DriverService driverService;

    @Autowired
    private VehicleService vehicleService;

    @Test
    public void should_add_driver(){
        User user = new User();
        String firstName = "Sydney";

        user.setFirstName(firstName);
        user.setLastName("Chauke");
        user.setEmail("sm@gamil.com");
        user.setIdNumber(ID_NUMBER);
        user.setStatus(Status.ACTIVE.getName());
        user.setCellNumber("+27721234567");

        DriverDetail driverDetail = new DriverDetail();
        driverDetail.setUser(user);
        driverDetail.setCrimeCheck("Yes");
        driverDetail.setDateLicenseObtained(new Date());
        driverDetail.setLicenseNumber(123456L);
        driverDetail.setPermitNumber("120FJ5");

        VehicleOwner owner = getVehicleOwner(user);
        driverDetail.setVehicleOwner(owner);
        Vehicle vehicle = getVehicle();
        driverDetail.setVehicle(vehicle);

        DriverDetail savedDriver = driverService.addDriver(driverDetail);

        assertThat(savedDriver.getId()).isNotNull();
        User savedUser = savedDriver.getUser();
        assertThat(savedUser).isNotNull();
        assertThat(savedUser.getId()).isNotZero();
        assertThat(savedUser.getFirstName()).isEqualTo(firstName);

    }

    @Test
    public void should_get_driver_by_idNumber(){

        should_add_driver();

        Optional<DriverDetail> driver =  driverService.findByIdNumber(ID_NUMBER);

        assertThat(driver.isPresent()).isTrue();
        DriverDetail driverDetail = driver.get();
        User user = driverDetail.getUser();
        assertThat(user).isNotNull();
        assertThat(user.getIdNumber()).isEqualTo(ID_NUMBER);

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

    private Vehicle getExistingVehicle() {
        String registrationNumber = "DS12GP";
        return vehicleService.findByRegistrationNumber(registrationNumber);
    }

    private VehicleOwner getVehicleOwner(User user) {
        VehicleOwner owner = new VehicleOwner();
        owner.setUser(user);
        owner.setDriver('N');
        return owner;
    }
}
