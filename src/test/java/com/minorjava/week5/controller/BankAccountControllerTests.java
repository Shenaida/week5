package com.minorjava.week5.controller;

import com.minorjava.week5.model.AccountStatus;
import com.minorjava.week5.model.BankAccount;
import com.minorjava.week5.service.BankAccountService;
import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.WebMvcTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.test.web.client.MockMvcClientHttpRequestFactory;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import java.util.Arrays;
import java.util.List;

import static org.hamcrest.Matchers.hasSize;
import static org.mockito.BDDMockito.given;
import static org.mockito.Mockito.verify;
import static org.mockito.internal.verification.VerificationModeFactory.times;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

@RunWith(SpringRunner.class)
@WebMvcTest(BankAccountController.class)
//@SpringBootTest
public class BankAccountControllerTests {

    @Autowired
    private MockMvc mockMvc;
    private BankAccountController controller;

    @MockBean
    BankAccountService bankAccountService;

//    @Test
//    public void contexLoads() throws Exception {
//        Assert.assertNotNull(controller);
//    }

    @Test
    public void WhenBankAccountAreRequested_ShouldReturnAllBankAccounts() throws Exception {

        BankAccount account = new BankAccount(1L,"INGB1", 12345678L, 100, AccountStatus.valueOf("ACTIVE"));

        List<BankAccount> allBankAccount = Arrays.asList(account);

        given(bankAccountService.findAll()).willReturn(allBankAccount);

        mockMvc.perform(get("/bankAccount"))
                .andExpect(status().isOk())
                .andExpect(content().contentType(MediaType.APPLICATION_JSON_UTF8))
                .andExpect(MockMvcResultMatchers.jsonPath("$", hasSize(1)))
                .andExpect(MockMvcResultMatchers.jsonPath("$[0].ibannumber").value("INGB1"));
    }

}
