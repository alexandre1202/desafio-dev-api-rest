package br.com.dockbank.bankaccount.mother;

import br.com.dockbank.bankaccount.domain.entity.AccountBank;
import br.com.dockbank.bankaccount.domain.entity.CustomerBank;
import java.math.BigDecimal;
import java.time.LocalDate;

public class AccountMother {

    private AccountMother() {}

    public static AccountBank getAccountBank(CustomerBank customerBank) {
        return new AccountBank(customerBank,
            new BigDecimal(20.21),
            new BigDecimal(1921.20), "A", 1, LocalDate.now());

    }

}
