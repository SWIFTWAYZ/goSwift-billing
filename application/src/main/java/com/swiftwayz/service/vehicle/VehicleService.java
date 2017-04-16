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
        Product product = vehicle.getProduct();
        Validate.notNull(product, "Product is required.");
        String productCode = product.getCode();
        boolean productPresent = productService.findByCode(productCode).isPresent();

        if(!productPresent){
            String message = "Product {" +
                    product.getCode() +
                    "} not found.";
            throw new RuntimeException(message);
        }
        return  vehicleRepository.save(vehicle);
    }


    public Vehicle updateVehicle(Vehicle vehicle) {
        return vehicleRepository.save(vehicle);
    }
}
