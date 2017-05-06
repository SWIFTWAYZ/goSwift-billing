package com.swiftwayz.service.driver;

import com.swiftwayz.GoSwiftApplication;
import com.swiftwayz.domain.user.Driver;
import com.swiftwayz.domain.user.DriverDetail;
import com.swiftwayz.domain.user.User;
import com.swiftwayz.domain.user.VehicleOwner;
import com.swiftwayz.domain.util.Status;
import com.swiftwayz.domain.vehicle.Product;
import com.swiftwayz.domain.vehicle.Vehicle;
import com.swiftwayz.service.vehicle.VehicleService;
import org.apache.commons.lang3.SerializationUtils;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

import static org.assertj.core.api.Assertions.assertThat;

/**
 * Created by sydney on 2017/04/17.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoSwiftApplication.class)
@ActiveProfiles("dev")
@Transactional
public class DriverServiceIntTest {

    public static final Long ID_NUMBER = 1234567899999L;
    public static final String SYDNEY = "Sydney";
    @Autowired
    private DriverService driverService;

    @Autowired
    private VehicleService vehicleService;

    private Driver savedDriver;

    @Test
    public void should_add_driver(){
        Driver driver = createDriver();

        DriverDetail driverDetail = getDriverDetail();
        driver.setDriverDetail(driverDetail);

        VehicleOwner owner = getVehicleOwner(driver);
        driverDetail.setVehicleOwner(owner);

        Vehicle vehicle = getVehicle();
        driverDetail.setVehicle(vehicle);

        savedDriver = driverService.addDriver(driver);

        assertThat(savedDriver.getId()).isNotNull();
        assertThat(savedDriver.getFirstName()).isEqualTo(SYDNEY);
        DriverDetail savedDriverDetail = savedDriver.getDriverDetail();
        assertThat(savedDriverDetail).isNotNull();
        assertThat(savedDriverDetail.getId()).isNotZero();

        Vehicle savedVehicle = savedDriver.getDriverDetail().getVehicle();
        assertThat(savedVehicle).isNotNull();
        assertThat(savedVehicle.getId()).isNotZero();

        Product product = savedVehicle.getProduct();
        assertThat(product).isNotNull();
        assertThat(product.getId()).isNotZero();

    }

    @Test
    public void should_get_driver_by_idNumber(){

        should_add_driver();

        Driver driver =  driverService.findByIdNumber(ID_NUMBER);

        assertThat(driver).isNotNull();
        assertThat(driver.getIdNumber()).isEqualTo(ID_NUMBER);
        assertThat(driver.getDriverDetail()).isNotNull();
        Vehicle vehicle = driver.getDriverDetail().getVehicle();
        assertThat(vehicle).isNotNull();
        assertThat(vehicle.getId()).isNotZero();
        assertThat(vehicle.getProduct()).isNotNull();
        assertThat(vehicle.getProduct().getId()).isNotZero();

    }

    @Test
    public void should_update_driver(){
        should_add_driver();
        String oldFirstName = savedDriver.getFirstName();
        Driver driver = SerializationUtils.clone( savedDriver);

        String driverName = "DriverName";
        driver.setFirstName(driverName);
        Driver updateDriver = driverService.updateDriver(driver);

        assertThat(updateDriver).isNotNull();
        assertThat(updateDriver.getFirstName()).isNotEqualTo(oldFirstName);
        assertThat(updateDriver.getFirstName()).isEqualTo(driverName);

    }

    private DriverDetail getDriverDetail() {
        DriverDetail driverDetail = new DriverDetail();
        driverDetail.setCrimeCheck("Yes");
        driverDetail.setDateLicenseObtained(new Date());
        driverDetail.setLicenseNumber(123456L);
        driverDetail.setPermitNumber("120FJ5");
        return driverDetail;
    }

    private Driver createDriver() {
        Driver driver = new Driver();
        driver.setFirstName(SYDNEY);
        driver.setLastName("Chauke");
        driver.setEmail("sm@gamil.com");
        driver.setIdNumber(ID_NUMBER);
        driver.setStatus(Status.ACTIVE.getName());
        driver.setCellNumber("+27721234567");
        return driver;
    }

    private Vehicle getVehicle() {
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("BMW");
        vehicle.setModel("320");
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
