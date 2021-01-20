package br.com.dockbank.bankaccount.repository;

import br.com.dockbank.bankaccount.domain.entity.CustomerBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface CustomerRepository extends JpaSpecificationExecutor<CustomerBank>, JpaRepository<CustomerBank, Long> {}
