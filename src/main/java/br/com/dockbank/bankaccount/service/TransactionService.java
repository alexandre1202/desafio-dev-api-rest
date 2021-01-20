package br.com.dockbank.bankaccount.service;

import br.com.dockbank.bankaccount.domain.request.TransactionRequest;
import br.com.dockbank.bankaccount.domain.response.TransactionResponse;

public interface TransactionService {
    TransactionResponse deposit(TransactionRequest request);
    TransactionResponse withdraw(TransactionRequest request);
}
