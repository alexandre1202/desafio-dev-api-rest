package br.com.dockbank.bankaccount.repository;

import static br.com.dockbank.bankaccount.mother.AccountMother.*;
import static br.com.dockbank.bankaccount.mother.CustomerMother.getCustomerBank;
import static org.assertj.core.api.Assertions.*;
import static org.assertj.core.api.Assertions.assertThat;

import br.com.dockbank.bankaccount.domain.entity.AccountBank;
import br.com.dockbank.bankaccount.domain.entity.CustomerBank;
import br.com.dockbank.bankaccount.mother.AccountMother;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.assertj.core.api.Assertions;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class AccountRepositoryTest {
    @Autowired
    private TestEntityManager em;

    @Autowired
    private AccountRepository repository;

    @Test
    void testAccountHappyPath() {
        CustomerBank customerBank = getCustomerBank();
        customerBank = em.persist(customerBank);
        em.flush();
        em.clear();

        AccountBank result = getAccountBank(customerBank);
        result = em.persist(result);
        em.flush();
        em.clear();

        assertThat(result.getBalance()).isEqualTo(new BigDecimal(20.21));
        assertThat(result.getWithdrawLimitPerDay()).isEqualTo(new BigDecimal(1921.20));
        assertThat(result.getAccountActive()).isEqualTo("A");
        assertThat(result.getAccountType()).isEqualTo(1);
        assertThat(result.getCustomerBank().getCustomerName()).isEqualTo("CustomerName");
        assertThat(result.getCustomerBank().getCustomerCpf()).isEqualTo("11111111111");
    }

    @Test
    void testFindAccountBankByAccountIdAndAccountActive() {
        CustomerBank customerBank = getCustomerBank();
        customerBank = em.persist(customerBank);
        em.flush();
        em.clear();

        AccountBank accountBank = getAccountBank(customerBank);
        accountBank = em.persist(accountBank);
        em.flush();
        em.clear();

        AccountBank result = repository
            .findAccountBankByAccountIdAndAccountActive(accountBank.getAccountId(),
                accountBank.getAccountActive());
        assertThat(result).isNotNull();
        assertThat(result.getAccountId()).isEqualTo(accountBank.getAccountId());

    }
}