package br.com.dockbank.bankaccount.repository;

import br.com.dockbank.bankaccount.domain.entity.AccountBank;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface AccountRepository extends JpaSpecificationExecutor<AccountBank>, JpaRepository<AccountBank, Long> {
    AccountBank findAccountBankByAccountIdAndAccountActive(Long accountId, String accountActive);
}
