package br.com.dockbank.bankaccount.service;

import br.com.dockbank.bankaccount.domain.request.AccountActivationRequest;
import br.com.dockbank.bankaccount.domain.request.AccountRequest;
import br.com.dockbank.bankaccount.domain.response.AccountResponse;

public interface AccountService {
    AccountResponse createAccount(AccountRequest request);
    AccountResponse activationAccount(Long accountId, AccountActivationRequest request);
    AccountResponse getAccountBalance(Long accountId);
}
