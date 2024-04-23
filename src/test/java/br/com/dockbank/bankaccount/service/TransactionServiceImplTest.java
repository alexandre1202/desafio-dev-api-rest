package br.com.dockbank.bankaccount.service;

import static br.com.dockbank.bankaccount.mother.AccountMother.getAccountBank;
import static br.com.dockbank.bankaccount.mother.CustomerMother.getCustomerBank;
import static br.com.dockbank.bankaccount.mother.TransactionMother.getTransactionBank;
import static br.com.dockbank.bankaccount.mother.TransactionMother.getTransactionRequest;
import static br.com.dockbank.bankaccount.mother.TransactionMother.getTransactionResponse;
import static java.math.BigDecimal.valueOf;
import static java.util.Arrays.asList;
import static org.assertj.core.api.Assertions.assertThat;
import static org.assertj.core.api.Assertions.assertThatThrownBy;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.ArgumentMatchers.anyString;
import static org.mockito.Mockito.any;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import br.com.dockbank.bankaccount.common.exception.UnprocessableEntityException;
import br.com.dockbank.bankaccount.domain.entity.AccountBank;
import br.com.dockbank.bankaccount.domain.entity.CustomerBank;
import br.com.dockbank.bankaccount.domain.entity.TransactionBank;
import br.com.dockbank.bankaccount.domain.mapper.TransactionMapper;
import br.com.dockbank.bankaccount.domain.request.TransactionRequest;
import br.com.dockbank.bankaccount.domain.response.TransactionResponse;
import br.com.dockbank.bankaccount.repository.AccountRepository;
import br.com.dockbank.bankaccount.repository.TransactionRepository;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.boot.test.context.SpringBootTest;

@ExtendWith(MockitoExtension.class)
class TransactionServiceImplTest {
    @Mock
    private TransactionRepository repository;

    @Mock
    private AccountRepository accountRepository;

    @Mock
    private TransactionMapper mapper;

    @InjectMocks
    private TransactionServiceImpl fixture;

    @Test
    void testDepositWithSuccess() {
        TransactionRequest request = getTransactionRequest(1L);
        CustomerBank customerBank = getCustomerBank();
        Optional<AccountBank> accountBankOptional = Optional.of(getAccountBank(customerBank));
        TransactionBank transactionBank = getTransactionBank(accountBankOptional.get());
        TransactionResponse transactionResponse = getTransactionResponse(transactionBank);

        when(accountRepository.findById(1L)).thenReturn(accountBankOptional);
        when(repository.save(any())).thenReturn(transactionBank);
        when(mapper.toResponse(transactionBank)).thenReturn(transactionResponse);

        TransactionResponse result = fixture.deposit(request);

        assertThat(result.getTransactionValue()).isBetween(valueOf(20300.54D), valueOf(20300.56D));
        assertThat(result.getTransactionCreated()).isNotNull();
    }

    @Test
    void testDepositWithNonexistentAccount() {
        TransactionRequest request = getTransactionRequest(999L);
        CustomerBank customerBank = getCustomerBank();
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
            .hasMessage("Account id not found");

        verify(accountRepository).findById(999L);
        verify(mapper, never()).toResponse(any(TransactionBank.class));
        verify(repository, never()).save(any(TransactionBank.class));
    }

    @Test
    void testDepositWithInvalidAccount() {
        TransactionRequest request = getTransactionRequest(1L);
        CustomerBank customerBank = getCustomerBank();
        AccountBank accountBank = getAccountBank(customerBank);
        accountBank.setAccountActive("D");
        Optional<AccountBank> accountBankOptional = Optional.of(accountBank);

        when(accountRepository.findById(1L)).thenReturn(accountBankOptional);

        assertThatThrownBy(() -> fixture.deposit(request))
            .isInstanceOf(UnprocessableEntityException.class)
            .hasMessage("Account invalid");

        verify(accountRepository).findById(1L);
        verify(mapper, never()).toResponse(any(TransactionBank.class));
        verify(repository, never()).save(any(TransactionBank.class));
    }

    @Test
    void testDepositWithNegativeBalance() {
        TransactionRequest request = getTransactionRequest(1L);
        request.setTransactionValue(valueOf(-1000));
        CustomerBank customerBank = getCustomerBank();
        AccountBank accountBank = getAccountBank(customerBank);
        Optional<AccountBank> accountBankOptional = Optional.of(accountBank);
        TransactionBank transactionBank = getTransactionBank(accountBankOptional.get());
        TransactionResponse transactionResponse = getTransactionResponse(transactionBank);

        when(accountRepository.findById(1L)).thenReturn(accountBankOptional);
        when(repository.save(any())).thenReturn(transactionBank);
        when(mapper.toResponse(transactionBank)).thenReturn(transactionResponse);

        assertThatThrownBy(() -> fixture.deposit(request))
            .isInstanceOf(UnprocessableEntityException.class)
            .hasMessage("Deposit can not be negative or zero");

        verify(accountRepository).findById(1L);
        verify(mapper, never()).toResponse(any(TransactionBank.class));
        verify(repository, never()).save(any(TransactionBank.class));
    }

    @Test
    void testWithdrawWithSuccess() {
        TransactionRequest request = getTransactionRequest(1L);
        request.setTransactionValue(valueOf(10));
        CustomerBank customerBank = getCustomerBank();
        Optional<AccountBank> accountBankOptional = Optional.of(getAccountBank(customerBank));
        TransactionBank transactionBank = getTransactionBank(accountBankOptional.get());
        TransactionResponse transactionResponse = getTransactionResponse(transactionBank);

        when(accountRepository.findById(1L)).thenReturn(accountBankOptional);
        when(repository.save(any())).thenReturn(transactionBank);
        when(mapper.toResponse(transactionBank)).thenReturn(transactionResponse);

        TransactionResponse result = fixture.withdraw(request);

        assertThat(result).isNotNull();
        assertThat(result.getTransactionValue()).isBetween(valueOf(20300.54D), valueOf(20300.56D));
        assertThat(result.getTransactionCreated()).isNotNull();
    }

    @Test
    void testWithdrawWithExcceded() {
        TransactionRequest request = getTransactionRequest(1L);
        request.setTransactionValue(valueOf(2000));
        CustomerBank customerBank = getCustomerBank();
        AccountBank accountBank = getAccountBank(customerBank);
        accountBank.setAccountId(1L);
        Optional<AccountBank> accountBankOptional = Optional.of(accountBank);
        List<TransactionBank> transactionsBank = asList(getTransactionBank(accountBankOptional.get()));
        TransactionResponse transactionResponse = getTransactionResponse(transactionsBank.get(0));

        when(accountRepository.findById(1L)).thenReturn(accountBankOptional);
        when(repository.findTransactionBanksByAccountBank_AccountIdAndAccountBank_AccountActiveAndTransactionCreatedEquals(
            anyLong(), anyString(), any(LocalDate.class))).thenReturn(transactionsBank);
        when(mapper.toResponse(transactionsBank.get(0))).thenReturn(transactionResponse);

        assertThatThrownBy(() -> fixture.withdraw(request))
            .isInstanceOf(UnprocessableEntityException.class)
            .hasMessage("Withdraw limit per day has exceeded");

        verify(accountRepository).findById(anyLong());
        verify(repository)
            .findTransactionBanksByAccountBank_AccountIdAndAccountBank_AccountActiveAndTransactionCreatedEquals(
                anyLong(), anyString(), any(LocalDate.class));
        verify(repository, never()).save(any(TransactionBank.class));
    }

    @Test
    void testWithdrawWithInsufficientBalance() {
        TransactionRequest request = getTransactionRequest(1L);
        request.setTransactionValue(valueOf(1900));
        CustomerBank customerBank = getCustomerBank();
        AccountBank accountBank = getAccountBank(customerBank);
        accountBank.setWithdrawLimitPerDay(valueOf(1000000));
        accountBank.setBalance(valueOf(1));
        accountBank.setAccountId(1L);
        Optional<AccountBank> accountBankOptional = Optional.of(accountBank);
        TransactionBank transactionBank = getTransactionBank(accountBankOptional.get());
        transactionBank.setTransactionValue(valueOf(1L));
        List<TransactionBank> transactionsBank = asList(transactionBank);
        TransactionResponse transactionResponse = getTransactionResponse(transactionsBank.get(0));

        when(accountRepository.findById(1L)).thenReturn(accountBankOptional);
        when(repository.findTransactionBanksByAccountBank_AccountIdAndAccountBank_AccountActiveAndTransactionCreatedEquals(
            anyLong(), anyString(), any(LocalDate.class))).thenReturn(transactionsBank);
        when(mapper.toResponse(transactionsBank.get(0))).thenReturn(transactionResponse);

        assertThatThrownBy(() -> fixture.withdraw(request))
            .isInstanceOf(UnprocessableEntityException.class)
            .hasMessage("Insufficient balance");

        verify(accountRepository).findById(anyLong());
        verify(repository)
            .findTransactionBanksByAccountBank_AccountIdAndAccountBank_AccountActiveAndTransactionCreatedEquals(
                anyLong(), anyString(), any(LocalDate.class));
        verify(repository, never()).save(any(TransactionBank.class));
    }

}
