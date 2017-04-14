package com.swiftwayz.web.rest;

import com.swiftwayz.domain.vehicle.Product;
import com.swiftwayz.service.product.ProductService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sydney on 2017/04/12.
 */
@RestController
@RequestMapping("/api")
public class ProductController {

    @Autowired
    private ProductService productService;

    /**
     * GET  /product/:code : get the product.
     *
     * @param code the productCode of the product to find
     * @return the ResponseEntity with status 200 (OK) and with body the product, or with status 404 (Not Found)
     */
    @GetMapping("/product/{code}")
    public ResponseEntity<Product> getProduct(@PathVariable String code){

        return productService.findByCode(code)
                .map(product -> new ResponseEntity<>(product,HttpStatus.OK))
                .orElse(new ResponseEntity<>(HttpStatus.NOT_FOUND));
    }

    @PostMapping(value = "/product",
        produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> addProduct(@RequestBody Product product){
        try {
            productService.add(product);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            String message = "Error adding Product code {"  + product.getCode() + "} " + ex.getMessage();
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }
    }

    @DeleteMapping(value = "/product")
    public ResponseEntity<?> deleteProduct(@RequestBody Product product){
        try {
            productService.delete(product);
            return new ResponseEntity<>(HttpStatus.OK);
        }catch (Exception ex){
            String message = "Error deleting Product code {"  + product.getCode() + "} " + ex.getMessage();
            return new ResponseEntity<>(message,HttpStatus.BAD_REQUEST);
        }
    }

}
