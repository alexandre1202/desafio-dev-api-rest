package br.com.dockbank.bankaccount.mother;

import br.com.dockbank.bankaccount.domain.entity.AccountBank;
import br.com.dockbank.bankaccount.domain.entity.TransactionBank;
import java.math.BigDecimal;
import java.time.LocalDate;

public class TransactionMother {

    private TransactionMother() {}

    public static TransactionBank getTransactionBank(AccountBank accountBank) {
        return new TransactionBank(accountBank,
            new BigDecimal(20300.55),
            LocalDate.now());
    }
}
