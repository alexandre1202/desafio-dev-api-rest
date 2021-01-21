package br.com.dockbank.bankaccount.repository;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.dockbank.bankaccount.domain.entity.AccountBank;
import br.com.dockbank.bankaccount.domain.entity.CustomerBank;
import java.math.BigDecimal;
import java.time.LocalDate;
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
        CustomerBank customerBank = new CustomerBank("CustomerName", "11111111111", LocalDate.now());
        customerBank = em.persist(customerBank);
        em.flush();
        em.clear();

        AccountBank result = new AccountBank(customerBank,
            new BigDecimal(20.21),
            new BigDecimal(1921.20), "A", 1, LocalDate.now());
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
}