package com.swiftwayz.service.vehicle;

import com.swiftwayz.GoSwiftApplication;
import com.swiftwayz.domain.vehicle.Product;
import com.swiftwayz.domain.vehicle.Vehicle;
import static org.assertj.core.api.Assertions.*;

import com.swiftwayz.repository.ProductRepository;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

import java.util.Date;

/**
 * Created by sydney on 2017/04/14.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoSwiftApplication.class)
@ActiveProfiles("dev")
@Transactional
public class VehicleServiceIntTest {

    @Autowired
    private VehicleService vehicleService;

    @Autowired
    private ProductRepository productRepository;

    private Vehicle savedVehicle;

    @Test
    public void should_add_vehicle(){
        Vehicle vehicle = new Vehicle();
        vehicle.setMake("Toyota");
        vehicle.setModel("corolla");
        vehicle.setClockMileage(4500);
        vehicle.setColor("White");
        vehicle.setDateApproved(new Date());
        vehicle.setSeatCapacity(4);
        vehicle.setVinNumber("21340/43/33");
        vehicle.setYearRegistered(2017);
        vehicle.setProduct(getProduct());


        Vehicle addVehicle = vehicleService.add(vehicle);

        assertThat(addVehicle.getId()).isNotZero();
        assertThat(addVehicle.getMake()).isEqualTo("Toyota");
        this.savedVehicle = addVehicle;
    }

    @Test
    public void should_update_vehicle(){
        should_add_vehicle();
        Vehicle vehicle = this.savedVehicle;

        vehicle.setModel("Avanza");
        Vehicle updated = vehicleService.updateVehicle(vehicle);

        assertThat(updated.getModel()).isEqualTo("Avanza");
    }

    @Test
    public void should_find_vehicle_by_registration_num(){

        String registrationNumber = "DS12GP";
        Vehicle vehicle = vehicleService.findByRegistrationNumber(registrationNumber);

        assertThat(vehicle).isNotNull();
        assertThat(vehicle.getRegistrationNumber()).isEqualTo(registrationNumber);

    }

    private Product getProduct() {
        return productRepository.findOneByCode("goX").get();
    }
}
