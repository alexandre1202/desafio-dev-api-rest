package br.com.dockbank.bankaccount.service;

import br.com.dockbank.bankaccount.common.exception.UnprocessableEntityException;
import br.com.dockbank.bankaccount.domain.entity.AccountBank;
import br.com.dockbank.bankaccount.domain.entity.CustomerBank;
import br.com.dockbank.bankaccount.domain.mapper.AccountMapper;
import br.com.dockbank.bankaccount.domain.request.AccountActivationRequest;
import br.com.dockbank.bankaccount.domain.request.AccountRequest;
import br.com.dockbank.bankaccount.domain.response.AccountResponse;
import br.com.dockbank.bankaccount.repository.AccountRepository;
import br.com.dockbank.bankaccount.repository.CustomerRepository;
import java.time.LocalDate;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional("transactionManager")
public class AccountServiceImpl {

    @Autowired
    private AccountMapper mapper;

    @Autowired
    private CustomerRepository customerRepository;

    @Autowired
    private AccountRepository repository;

    public AccountResponse getAccountBalance(Long accountId) {
        return mapper.toResponse(repository.findAccountBankByAccountIdAndAccountActive(accountId, "A"));
    }

    public AccountResponse createAccount(AccountRequest request) {
        CustomerBank customerBank = customerRepository
            .findById(request.getCustomerId())
            .orElseThrow(() -> new UnprocessableEntityException("Customer id not found"));

        AccountBank accountBank = new AccountBank(customerBank, request.getBalance(),
            request.getWithdrawLimitPerDay(), request.getAccountActive(), request.getAccountType(),
            LocalDate.now());

        return mapper.toResponse(repository.save(accountBank));
    }

    public AccountResponse activationAccount(Long accountId, AccountActivationRequest request) {
        AccountBank accountBank = repository
            .findById(accountId)
            .orElseThrow(() -> new UnprocessableEntityException("Account id not found"));
        accountBank.setAccountActive(request.getStatus());
        accountBank = repository.save(accountBank);

        return mapper.toResponse(repository.save(accountBank));
    }
}
