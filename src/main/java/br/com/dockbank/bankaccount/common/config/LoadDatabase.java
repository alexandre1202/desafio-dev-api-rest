package br.com.dockbank.bankaccount.common.config;

import br.com.dockbank.bankaccount.domain.entity.CustomerBank;
import br.com.dockbank.bankaccount.repository.CustomerRepository;
import java.time.LocalDate;
import org.springframework.boot.CommandLineRunner;
import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;

@Configuration
public class LoadDatabase {

    @Bean
    CommandLineRunner initBankAccountData(CustomerRepository customerRepository) {
        return args -> customerRepository.save(new CustomerBank("Initial Customer Name", "11111111111", LocalDate.now()));
    }
}
