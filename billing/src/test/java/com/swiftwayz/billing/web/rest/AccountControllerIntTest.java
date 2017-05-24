package com.swiftwayz.billing.web.rest;

import com.swiftwayz.BillingApplication;
import com.swiftwayz.billing.service.AccountService;
import com.swiftwayz.domain.billing.Account;
import com.swiftwayz.domain.util.Status;
import org.assertj.core.api.Assertions;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.MockitoAnnotations;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.http.MediaType;
import org.springframework.test.context.ActiveProfiles;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.util.ReflectionTestUtils;
import org.springframework.test.web.servlet.MockMvc;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.*;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import org.springframework.test.web.servlet.MvcResult;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;
import org.springframework.transaction.annotation.Transactional;

import java.io.IOException;
import java.math.BigDecimal;
import java.util.Date;

/**
 * Created by sydney on 2017/05/21.
 */
@RunWith(SpringRunner.class)
@SpringBootTest(classes = BillingApplication.class)
@ActiveProfiles("dev")
@Transactional
public class AccountControllerIntTest {

    @Autowired
    private AccountService accountService;

    private MockMvc restMvc;
    private MockMvc restMvcMock;

    @Before
    public void setUp(){
        MockitoAnnotations.initMocks(this);
        AccountController accountController = new AccountController();
        ReflectionTestUtils.setField(accountController, "accountService", accountService);

        restMvc = MockMvcBuilders.standaloneSetup(accountController).build();
    }

    @Test
    public void should_add_account() throws Exception {
        Account account = createAccount();

        restMvc.perform(
                post("/billing/account")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(account)))
        .andExpect(status().isOk());
    }

    @Test
    public void should_fail_to_add_account_of_invalid_user() throws Exception {
        Account account = createAccount();
        account.setUserId(-1L);

        MvcResult mvcResult = restMvc.perform(
                post("/billing/account")
                        .contentType(TestUtil.APPLICATION_JSON_UTF8)
                        .content(TestUtil.convertObjectToJsonBytes(account)))
                .andExpect(status().isBadRequest())
                .andReturn();

        String content = mvcResult.getResponse().getContentAsString();
        Assertions.assertThat(content).contains("User (-1) does not exist.");
    }

    @Test
    public void should_find_account() throws Exception {

        restMvc.perform(
                get("/billing/account/1002")
                .accept(MediaType.APPLICATION_JSON))
                .andExpect(status().isOk())
                .andExpect(jsonPath("$.accountId").value(1002));
    }

    @Test
    public void should_debit_account_with_100(){


    }

    private Account createAccount() {
        Account account = new Account();

        account.setBalance(BigDecimal.ZERO);
        account.setName("goSwift");
        account.setDescription("goSwift");
        account.setType("Swift");
        account.setOpenDate(new Date());
        account.setStatus(Status.ACTIVE.getName());
        account.setUserId(Long.valueOf(1));
        return account;
    }

}
