package com.swiftwayz.service.vehicle;

import com.swiftwayz.domain.vehicle.Product;
import com.swiftwayz.domain.vehicle.Vehicle;
import com.swiftwayz.repository.ProductRepository;
import com.swiftwayz.repository.VehicleRepository;
import com.swiftwayz.service.product.ProductService;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;
import org.springframework.util.Assert;

import java.util.Optional;

/**
 * Created by sydney on 2017/04/08.
 */
@Component
public class VehicleService {

    @Autowired
    private VehicleRepository vehicleRepository;

    @Autowired
    private ProductService productService;

    public Vehicle add(Vehicle vehicle){
        validateProduct(vehicle);
        return  vehicleRepository.save(vehicle);
    }

    public Vehicle updateVehicle(Vehicle vehicle) {
        validateProduct(vehicle);
        return vehicleRepository.save(vehicle);
    }

    public void validateProduct(Vehicle vehicle) {
        Product product = vehicle.getProduct();
        Validate.notNull(product, "Product is required.");
        String productCode = product.getCode();
        Product existingProduct = productService.findByCode(productCode).orElse(null);

        Validate.notNull(existingProduct, String.format("Product {%s} not found.", product));
        vehicle.setProduct(existingProduct);
    }

    public Vehicle findByRegistrationNumber(String registrationNumber){

        Optional<Vehicle> vehicle = vehicleRepository.findOneByRegistrationNumber(registrationNumber);

        if(vehicle.isPresent()){
            return vehicle.get();
        } else {
            String message = "Vehicle with registration " +
                    registrationNumber + ", not found.";
            throw new RuntimeException(message);
        }
    }

    public void remove(Vehicle vehicle) {
        vehicleRepository.delete(vehicle);
    }


    public void remove(Long id) {
        vehicleRepository.delete(id);
    }
}
