package com.swiftwayz.repository;

import com.swiftwayz.domain.vehicle.Product;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

/**
 * Created by sydney on 2017/04/09.
 */
@Repository
public interface ProductRepository extends JpaRepository <Product, Long>{
}
