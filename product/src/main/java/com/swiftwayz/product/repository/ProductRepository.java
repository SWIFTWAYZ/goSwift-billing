package com.swiftwayz.product.repository;

import com.swiftwayz.domain.vehicle.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import java.util.Optional;

/**
 * Created by sydney on 2017/04/09.
 */
@Repository
public interface ProductRepository extends JpaRepository <Product, Long>{

    Optional<Product> findOneByCode(String code);

}
