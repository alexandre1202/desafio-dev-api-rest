package br.com.dockbank.bankaccount.repository;

import br.com.dockbank.bankaccount.domain.entity.TransactionBank;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;

public interface TransactionRepository extends JpaSpecificationExecutor<JpaRepository>, JpaRepository<TransactionBank, Long> {
List<TransactionBank> findTransactionBanksByAccountBank_AccountIdAndAccountBank_AccountActiveAndTransactionCreatedEquals(Long accountId, String status, LocalDate localDate);

}
