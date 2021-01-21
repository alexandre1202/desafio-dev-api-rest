package br.com.dockbank.bankaccount.repository;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.dockbank.bankaccount.domain.entity.AccountBank;
import br.com.dockbank.bankaccount.domain.entity.CustomerBank;
import br.com.dockbank.bankaccount.domain.entity.TransactionBank;
import java.math.BigDecimal;
import java.time.LocalDate;
import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;

@DataJpaTest
class TransactionRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private TransactionRepository repository;

    @Test
    void testTransactionHappyPath() {
        CustomerBank customerBank = new CustomerBank("CustomerName", "11111111111", LocalDate.now());
        customerBank = em.persist(customerBank);
        em.flush();
        em.clear();

        AccountBank accountBank = new AccountBank(customerBank,
            new BigDecimal(20.21),
            new BigDecimal(1921.20), "A", 1, LocalDate.now());
        accountBank = em.persist(accountBank);
        em.flush();
        em.clear();

        TransactionBank result = new TransactionBank(accountBank,
            new BigDecimal(20300.55),
            LocalDate.now());

        result = em.persist(result);
        em.flush();
        em.clear();

        assertThat(result.getAccountBank()).isEqualTo(accountBank);
        assertThat(result.getTransactionCreated()).isNotNull();
        assertThat(result.getTransactionValue()).isEqualTo(new BigDecimal(20300.55));
    }
}