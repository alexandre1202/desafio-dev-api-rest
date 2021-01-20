package br.com.dockbank.bankaccount.domain.types;

import java.util.Arrays;

public interface AccountBank {

    String getValue();

    static AccountBank getEnumInstance(AccountBank[] enums, String value) {
        return Arrays.stream(enums)
            .filter(e -> e.getValue().equals(value))
            .findFirst().orElse(null);
    }
}