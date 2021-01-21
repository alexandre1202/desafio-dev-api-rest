package br.com.dockbank.bankaccount.mother;

import br.com.dockbank.bankaccount.domain.entity.CustomerBank;
import java.time.LocalDate;

public class CustomerMother {

    private CustomerMother() {}

    public static CustomerBank getCustomerBank() {
        return new CustomerBank("CustomerName", "11111111111", LocalDate
            .now());
    }
}
