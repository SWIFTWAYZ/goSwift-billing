package com.swiftwayz.billing.service;

import com.swiftwayz.BillingApplication;
import com.swiftwayz.domain.billing.Bill;
import static org.assertj.core.api.Assertions.*;

import com.swiftwayz.domain.billing.Trip;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.transaction.annotation.Transactional;

/**
 * Created by sydney on 2017/05/29.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BillingApplication.class)
@ActiveProfiles("dev")
@Transactional
public class BillingServiceIntTest {

    @Autowired
    private BillingService billingService;

    @Test
    public void should_create_a_bill(){

        Bill bill = new Bill();
        bill.setUserId(1L);

        Trip trip = new Trip();
        trip.setKm(6.9);
        bill.setTrip(trip);

        bill.setDriverId(1L);

        billingService.createBill(bill);

        assertThat(bill.getId()).isNotZero();
        assertThat(trip.getId()).isNotZero();

    }
}
