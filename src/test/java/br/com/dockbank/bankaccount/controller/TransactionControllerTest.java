package br.com.dockbank.bankaccount.controller;

import static br.com.dockbank.bankaccount.mother.AccountMother.getAccountBank;
import static br.com.dockbank.bankaccount.mother.CustomerMother.getCustomerBank;
import static br.com.dockbank.bankaccount.mother.TransactionMother.getTransactionBank;
import static br.com.dockbank.bankaccount.mother.TransactionMother.getTransactionRequest;
import static br.com.dockbank.bankaccount.mother.TransactionMother.getTransactionResponse;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.post;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.jsonPath;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import br.com.dockbank.bankaccount.domain.entity.AccountBank;
import br.com.dockbank.bankaccount.domain.entity.TransactionBank;
import br.com.dockbank.bankaccount.domain.request.TransactionRequest;
import br.com.dockbank.bankaccount.domain.response.TransactionResponse;
import br.com.dockbank.bankaccount.service.TransactionServiceImpl;
import com.fasterxml.jackson.databind.ObjectMapper;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.Spy;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.setup.MockMvcBuilders;

class TransactionControllerTest {

    private final String DEPOSIT = "/v1/deposits";
    private final String WITHDRAW = "/v1/withdraws";

    @InjectMocks
    private TransactionController fixture;

    @Mock
    private TransactionServiceImpl transactionService;

    @Spy
    @Autowired
    private ObjectMapper mapper;

    private TransactionRequest request;
    protected MockMvc mockMvc;

    @BeforeEach
    void setup() {
        initMocks(this);
        mockMvc = MockMvcBuilders.standaloneSetup(fixture).build();
        request = getTransactionRequest(1L);
    }

    @Test
    void testDepositWithSuccess() throws Exception {
        Long accountId = 1L;
        String json = mapper.writeValueAsString(getTransactionRequest(accountId));
        AccountBank accountBank = getAccountBank(getCustomerBank());
        accountBank.setAccountId(accountId);
        TransactionBank transactionBank = getTransactionBank(accountBank);
        transactionBank.setTransactionId(1L);
        TransactionResponse response = getTransactionResponse(transactionBank);
        when(transactionService.deposit(any())).thenReturn(response);

        mockMvc.perform(post(DEPOSIT).contentType(APPLICATION_JSON_VALUE)
            .accept(APPLICATION_JSON_VALUE)
            .content(json))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.transactionId").value(1L))
            .andExpect(jsonPath("$.accountId").value(1L))
            .andExpect(jsonPath("$.transactionValue").value(20300.55D))
            .andExpect(jsonPath("$.transactionCreated").isNotEmpty())
            .andDo(print());
    }

    @Test
    void testWithdrawWithSuccess() throws Exception {
        Long accountId = 1L;
        String json = mapper.writeValueAsString(getTransactionRequest(accountId));
        AccountBank accountBank = getAccountBank(getCustomerBank());
        accountBank.setAccountId(accountId);
        TransactionBank transactionBank = getTransactionBank(accountBank);
        transactionBank.setTransactionId(1L);
        TransactionResponse response = getTransactionResponse(transactionBank);
        when(transactionService.withdraw(any())).thenReturn(response);

        mockMvc.perform(post(WITHDRAW).contentType(APPLICATION_JSON_VALUE)
            .accept(APPLICATION_JSON_VALUE)
            .content(json))
            .andExpect(status().isCreated())
            .andExpect(jsonPath("$.transactionId").value(1L))
            .andExpect(jsonPath("$.accountId").value(1L))
            .andExpect(jsonPath("$.transactionValue").value(20300.55D))
            .andExpect(jsonPath("$.transactionCreated").isNotEmpty())
            .andDo(print());
    }
}