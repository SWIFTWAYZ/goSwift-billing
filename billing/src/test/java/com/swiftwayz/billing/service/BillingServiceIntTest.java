package com.swiftwayz.billing.service;

import com.swiftwayz.BillingApplication;
import com.swiftwayz.billing.service.reset.DriverRestService;
import com.swiftwayz.billing.service.reset.ProductRestService;
import com.swiftwayz.domain.billing.Bill;
import static org.assertj.core.api.Assertions.*;

import com.swiftwayz.domain.billing.Trip;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
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

    @MockBean
    private ProductRestService productRestService;

    @MockBean
    private DriverRestService driverRestService;

    @Test
    public void should_create_a_bill(){
        Bill bill = createBill();

        billingService.createBill(bill);

        assertThat(bill.getId()).isNotZero();
        assertThat(bill.getTrip().getId()).isNotZero();
    }

    private Bill createBill() {
        Bill bill = new Bill();
        bill.setUserId(1L);
        bill.setDriverId(1L);

        Trip trip = createTrip();
        bill.setTrip(trip);

        return bill;
    }

    private Trip createTrip() {
        Trip trip = new Trip();
        trip.setKm(6.9);
        return trip;
    }
}
