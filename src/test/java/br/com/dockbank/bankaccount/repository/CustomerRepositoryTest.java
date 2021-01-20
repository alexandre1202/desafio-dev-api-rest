package br.com.dockbank.bankaccount.repository;

import static org.assertj.core.api.Assertions.assertThat;

import br.com.dockbank.bankaccount.domain.entity.CustomerBank;
import java.time.LocalDateTime;
import org.junit.jupiter.api.Test;
//import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.orm.jpa.DataJpaTest;
import org.springframework.boot.test.autoconfigure.orm.jpa.TestEntityManager;
import org.springframework.test.context.junit4.SpringRunner;

//@RunWith(SpringRunner.class)
@DataJpaTest
class CustomerRepositoryTest {

    @Autowired
    private TestEntityManager em;

    @Autowired
    private CustomerRepository repository;

//    @Test()
//    void testCustomerHappyPath() {
//        CustomerBank create = new CustomerBank("CustomerName", "11111111111", LocalDateTime.now());
//        create = em.persist(create);
//        em.flush();
//        em.clear();
//
//        CustomerBank result = repository.findById(create.getCustomerId()).get();
//        assertThat(result.getCustomerName()).isEqualTo("CustomerName");
//        assertThat(result.getCustomerCpf()).isEqualTo("11111111111");
//    }
}