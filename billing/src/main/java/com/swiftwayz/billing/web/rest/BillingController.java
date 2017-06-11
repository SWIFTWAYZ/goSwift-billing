package com.swiftwayz.billing.web.rest;


import com.swiftwayz.billing.service.BillingService;
import com.swiftwayz.domain.billing.Bill;
import com.swiftwayz.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/billing")
public class BillingController extends BaseController{

    @Autowired
    private BillingService billingService;

    @PostMapping( value = "/bill",
            produces = MediaType.TEXT_PLAIN_VALUE)
    public ResponseEntity<?> createbill(@RequestBody Bill bill){
        try {
            Bill savedBill = billingService.createBill(bill);
            return httpOk(savedBill);
        } catch (Exception ex){
            return httpBadRequest(ex);
        }
    }
}
