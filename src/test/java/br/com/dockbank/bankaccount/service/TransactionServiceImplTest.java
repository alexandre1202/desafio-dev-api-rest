package br.com.dockbank.bankaccount.service;

import static br.com.dockbank.bankaccount.mother.AccountMother.*;
import static br.com.dockbank.bankaccount.mother.TransactionMother.getTransactionBank;
import static br.com.dockbank.bankaccount.mother.TransactionMother.getTransactionRequest;
import static br.com.dockbank.bankaccount.mother.TransactionMother.getTransactionResponse;
import static java.math.BigDecimal.valueOf;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.verifyNoMoreInteractions;
import static org.mockito.Mockito.when;

import br.com.dockbank.bankaccount.common.exception.UnprocessableEntityException;
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
        Optional<AccountBank> accountBankOptional = Optional.of(getAccountBank(customerBank));
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
    void testDepositWithInvalidAccount() {
        TransactionRequest request = getTransactionRequest(1L);
        CustomerBank customerBank = CustomerMother.getCustomerBank();
        AccountBank accountBank = getAccountBank(customerBank);
        accountBank.setAccountActive("D");
        Optional<AccountBank> accountBankOptional = Optional.of(accountBank);
        TransactionBank transactionBank = getTransactionBank(accountBankOptional.get());
        TransactionResponse transactionResponse = getTransactionResponse(transactionBank);

        when(accountRepository.findById(1L)).thenReturn(accountBankOptional);
        when(repository.save(any())).thenReturn(transactionBank);
        when(mapper.toResponse(transactionBank)).thenReturn(transactionResponse);

        assertThatThrownBy(() -> fixture.deposit(request))
            .isInstanceOf(UnprocessableEntityException.class)
            .hasMessage("Account invalid");

        verify(accountRepository).findById(1L);
        verify(mapper, never()).toResponse(any(TransactionBank.class));
        verify(repository, never()).save(any(TransactionBank.class));
    }
}