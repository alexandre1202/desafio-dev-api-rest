package br.com.dockbank.bankaccount.mother;

import br.com.dockbank.bankaccount.domain.entity.AccountBank;
import br.com.dockbank.bankaccount.domain.entity.TransactionBank;
import br.com.dockbank.bankaccount.domain.request.TransactionRequest;
import br.com.dockbank.bankaccount.domain.response.TransactionResponse;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionMother {

    private TransactionMother() {}

    public static TransactionBank getTransactionBank(AccountBank accountBank) {
        return new TransactionBank(accountBank,
            new BigDecimal(20300.55),
            LocalDate.now());
    }

    public static TransactionRequest getTransactionRequest(Long accountId) {
        return new TransactionRequest(accountId, BigDecimal.valueOf(1500D));
    }

    public static TransactionResponse getTransactionResponse(TransactionBank transactionBank) {
        return new TransactionResponse(transactionBank.getTransactionId(), transactionBank.getAccountBank().getAccountId(),
            transactionBank.getTransactionValue(), transactionBank.getTransactionCreated());
    }
}
