package com.swiftwayz.web.rest;

import com.swiftwayz.GoSwiftApplication;
import com.swiftwayz.domain.vehicle.Product;
import com.swiftwayz.service.product.ProductService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;

import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.*;

/**
 * Created by sydney on 2017/04/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = GoSwiftApplication.class)
@ActiveProfiles("dev")
@Transactional
public class ProductControllerIntTest {

    @Autowired
    private ProductService productService;

    private MockMvc restMvc;

    @Before
    public void setUp(){
        ProductController productController = new ProductController();
        ReflectionTestUtils.setField(productController, "productService", productService);

        restMvc = MockMvcBuilders.standaloneSetup(productController).build();
    }


    @Test
    public void should_get_goX_product_by_code() throws Exception {

        restMvc.perform(get("/api/product/goX")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("goX"))
                .andExpect(jsonPath("$.name").value("goX 3 Seater")
                );

    }
}
