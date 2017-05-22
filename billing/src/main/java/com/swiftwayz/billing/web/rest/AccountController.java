package com.swiftwayz.billing.web.rest;

import com.swiftwayz.billing.service.AccountService;
import com.swiftwayz.domain.billing.Account;
import com.swiftwayz.domain.billing.BankingTx;
import com.swiftwayz.web.BaseController;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

/**
 * Created by sydney on 2017/05/21.
 */
@RestController
@RequestMapping("/billing")
public class AccountController extends BaseController{

    @Autowired
    private AccountService accountService;

    @PostMapping("/account")
    public ResponseEntity<?> addAccount(@RequestBody Account account){
        try {
            Account savedAccount = accountService.addAccount(account);
            return httpOk(savedAccount);
        } catch (Exception ex){
            return httpBadRequest(ex);
        }
    }

    @PostMapping("/deposit")
    public ResponseEntity<?> deposit(@RequestBody BankingTx bankingTx){
        try{
            accountService.debitAccount(bankingTx);
            return httpOk();
        } catch (Exception ex){
            return httpBadRequest(ex);
        }
    }
}
