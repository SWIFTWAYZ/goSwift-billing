package com.swiftwayz.product.web.rest;

import com.swiftwayz.domain.vehicle.Product;
import com.swiftwayz.product.service.ProductService;
import com.swiftwayz.web.BaseController;
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
public class ProductController extends BaseController {

    @Autowired
    private ProductService productService;

    /**
     * GET  /product/:code : get the product.
     *
     * @param code the productCode of the product to find
     * @return the ResponseEntity with status 200 (OK) and with body the product, or with status 404 (Not Found)
     */
    @GetMapping("/product/{code}")
    public ResponseEntity<?> getProduct(@PathVariable String code) {
        try {
            Product product = productService.getProduct(code);
            return httpOk(product);
        } catch (Exception ex) {
            return httpBadRequest(ex);
        }
    }

    @PostMapping(value = "/product",
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> addProduct(@RequestBody Product product) {
        productService.add(product);
        return httpOk(product);
    }

    @DeleteMapping(value = "/product")
    public ResponseEntity<?> deleteProduct(@RequestBody Product product) {
        try {
            productService.delete(product);
            return httpOk();
        } catch (Exception ex) {
            String message = "Error deleting Product code {" + product.getCode() + "} " + ex.getMessage();
            return httpBadRequest(message);
        }
    }

    @PutMapping(value = "/product")
    public ResponseEntity<?> updateProduct(@RequestBody Product product) {
        try {
            productService.update(product);
            return httpOk();
        } catch (Exception ex) {
            String message = "Error Updating Product code {" + product.getCode() + "} " + ex.getMessage();
            return httpBadRequest(message);
        }
    }

}
