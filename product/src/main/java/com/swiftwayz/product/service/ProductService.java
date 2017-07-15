package com.swiftwayz.product.service;

import com.swiftwayz.domain.vehicle.Product;
import com.swiftwayz.product.repository.ProductRepository;
import org.apache.commons.lang3.Validate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.Optional;

/**
 * Created by sydney on 2017/04/09.
 */
@Service
@Transactional
public class ProductService {

    @Autowired
    private ProductRepository productRepository;

    public Product add(Product product) {
        return productRepository.save(product);
    }

    private Optional<Product> findByCode(String code) {
        return productRepository.findOneByCode(code);
    }

    public Product getProduct(String code){
        Product product = new Product();
        product.setCode(code);
        return getProduct(product);
    }

    public Product getProduct(Product product){
        Validate.notNull(product, "Product is required.");
        String productCode = product.getCode();
        Product existingProduct = findByCode(productCode).orElse(null);
        Validate.notNull(existingProduct, String.format("Product {%s} not found.", productCode));
        return existingProduct;
    }

    public Product update(Product product) {
        return productRepository.save(product);
    }

    public void delete(Product product) {
        productRepository.delete(product);
    }
}
