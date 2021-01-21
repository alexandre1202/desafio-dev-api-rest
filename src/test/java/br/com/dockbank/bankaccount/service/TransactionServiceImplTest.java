package br.com.dockbank.bankaccount.service;

import static br.com.dockbank.bankaccount.mother.TransactionMother.getTransactionBank;
import static br.com.dockbank.bankaccount.mother.TransactionMother.getTransactionRequest;
import static br.com.dockbank.bankaccount.mother.TransactionMother.getTransactionResponse;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.when;

import br.com.dockbank.bankaccount.domain.entity.AccountBank;
import br.com.dockbank.bankaccount.domain.entity.CustomerBank;
import br.com.dockbank.bankaccount.domain.entity.TransactionBank;
import br.com.dockbank.bankaccount.domain.mapper.TransactionMapper;
import br.com.dockbank.bankaccount.domain.request.TransactionRequest;
import br.com.dockbank.bankaccount.domain.response.TransactionResponse;
import br.com.dockbank.bankaccount.mother.AccountMother;
import br.com.dockbank.bankaccount.mother.CustomerMother;
import br.com.dockbank.bankaccount.repository.AccountRepository;
import br.com.dockbank.bankaccount.repository.TransactionRepository;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.springframework.boot.test.context.SpringBootTest;

@SpringBootTest
class TransactionServiceImplTest {

    @InjectMocks
    private TransactionServiceImpl fixture;

    @Mock
    private TransactionRepository repository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionMapper mapper;

    @Test
    void testDepositWithSuccess() {
        TransactionRequest request = getTransactionRequest(1L);
        CustomerBank customerBank = CustomerMother.getCustomerBank();
        Optional<AccountBank> accountBankOptional = Optional.of(AccountMother.getAccountBank(customerBank));
        TransactionBank transactionBank = getTransactionBank(accountBankOptional.get());
        TransactionResponse transactionResponse = getTransactionResponse(transactionBank);

        when(accountRepository.findById(1L)).thenReturn(accountBankOptional);
        when(repository.save(any())).thenReturn(transactionBank);
        when(mapper.toResponse(transactionBank)).thenReturn(transactionResponse);

        TransactionResponse result = fixture.deposit(request);

        assertThat(result).isNotNull();
        assertThat(result.getTransactionValue()).isBetween(valueOf(20300.54D), valueOf(20300.56D));
        assertThat(result.getTransactionCreated()).isNotNull();
    }

    @Test
    void testDepositWith() {
        TransactionRequest request = getTransactionRequest(1L);
        CustomerBank customerBank = CustomerMother.getCustomerBank();
        Optional<AccountBank> accountBankOptional = Optional.of(AccountMother.getAccountBank(customerBank));
        TransactionBank transactionBank = getTransactionBank(accountBankOptional.get());
        TransactionResponse transactionResponse = getTransactionResponse(transactionBank);

        when(accountRepository.findById(1L)).thenReturn(accountBankOptional);
        when(repository.save(any())).thenReturn(transactionBank);
        when(mapper.toResponse(transactionBank)).thenReturn(transactionResponse);

        TransactionResponse result = fixture.deposit(request);

        assertThat(result).isNotNull();
        assertThat(result.getTransactionValue()).isBetween(valueOf(20300.54D), valueOf(20300.56D));
        assertThat(result.getTransactionCreated()).isNotNull();
    }
}