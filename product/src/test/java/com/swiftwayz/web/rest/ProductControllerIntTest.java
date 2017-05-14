package com.swiftwayz.web.rest;

import com.swiftwayz.ProductApplication;
import com.swiftwayz.domain.vehicle.Product;
import com.swiftwayz.product.service.ProductService;
import com.swiftwayz.product.web.rest.ProductController;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

/**
 * Created by sydney on 2017/04/12.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = ProductApplication.class)
@ActiveProfiles("dev")
@Transactional
public class ProductControllerIntTest {

    @Autowired
    private ProductService productService;

    @Mock
    private ProductService productServiceMock;

    private MockMvc restMvc;
    private MockMvc restMvcMock;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        ProductController productController = new ProductController();
        ReflectionTestUtils.setField(productController, "productService", productService);

        ProductController productControllerMock = new ProductController();
        ReflectionTestUtils.setField(productControllerMock, "productService", productServiceMock);

        restMvc = MockMvcBuilders.standaloneSetup(productController).build();
        restMvcMock = MockMvcBuilders.standaloneSetup(productControllerMock).build();

    }

    @Test
    public void should_add_test_product() throws Exception {
        Product product = getProduct();

        restMvc.perform(
                post("/api/product")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(product)))
                .andExpect(status().isOk());
    }

    @Test
    public void should_throw_exception_when_add_test_product() throws Exception {
        Product product = getProduct();

        when(productServiceMock.add(any(Product.class))).thenThrow(new RuntimeException());
        MvcResult mvcResult = restMvcMock.perform(
                post("/api/product")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(product)))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        assertThat(content).contains("Error adding Product code");
    }

    @Test
    public void should_get_goX_product_by_code() throws Exception {
        should_add_test_product();
        restMvc.perform(get("/api/product/goTest")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.code").value("goTest"))
                .andExpect(jsonPath("$.name").value("go Test"));
    }

    private Product getProduct() {
        Product product = new Product();
        product.setCode("goTest");
        product.setName("go Test");
        product.setDescription("got Test product");
        return product;
    }
}
