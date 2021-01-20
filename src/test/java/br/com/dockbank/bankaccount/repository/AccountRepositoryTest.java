package br.com.dockbank.bankaccount.repository;

import static org.assertj.core.api.Assertions.*;

import br.com.dockbank.bankaccount.domain.entity.AccountBank;
import br.com.dockbank.bankaccount.domain.entity.CustomerBank;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
@DataJpaTest
class AccountRepositoryTest {
    @Autowired
    private TestEntityManager em;

    @Autowired
    private AccountRepository repository;

//    @Test
//    void testAccountHappyPath() {
//        CustomerBank customerBank = new CustomerBank("CustomerName", "11111111111", LocalDateTime.now());
//        customerBank = em.persist(customerBank);
//        em.flush();
//        em.clear();
//
//        AccountBank result = new AccountBank(customerBank,
//            new BigDecimal(20.21),
//            new BigDecimal(1921.20), Boolean.TRUE, 1, LocalDateTime.now());
//        result = em.persist(result);
//        em.flush();
//        em.clear();
//
//        assertThat(result.getBalance()).isEqualTo(new BigDecimal(20.21));
//        assertThat(result.getWithdrawLimitPerDay()).isEqualTo(new BigDecimal(1921.20));
//        assertThat(result.getAccountActive()).isEqualTo(1);
//        assertThat(result.getAccountType()).isEqualTo(1);
//        assertThat(result.getCustomerBank().getCustomerName()).isEqualTo("CustomerName");
//        assertThat(result.getCustomerBank().getCustomerCpf()).isEqualTo("11111111111");
//    }
}