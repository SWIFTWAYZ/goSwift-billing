package com.swiftwayz.billing.service.reset;

import com.swiftwayz.domain.vehicle.Product;
import com.swiftwayz.web.RESTEnv;
import com.swiftwayz.web.RESTServer;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

/**
 * Created by sydney on 2017/05/31.
 */
@Component
public class ProductRestService {

    public static final String URL = "http://%s:%s/swift/api/product";
    public static final String PRODUCT = "PRODUCT";
    @Autowired
    private RESTEnv restEnv;

    @Autowired
    private RESTServer restServer;

    public Product getProduct(Product product){
        String url = String.format(URL,  restEnv.getHost(), restEnv.getPort(),PRODUCT);
        return restServer.post(url, Product.class, product);
    }
}
