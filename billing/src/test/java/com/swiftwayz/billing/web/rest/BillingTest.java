package com.swiftwayz.billing.web.rest;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

@RunWith(SpringRunner.class)
@SpringBootTest(classes = BillingController.class)
public class BillingTest {

    @Test
    public void contextLoads() {
        Assert.assertTrue(true);
    }

}
