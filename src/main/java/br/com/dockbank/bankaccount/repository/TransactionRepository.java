package br.com.dockbank.bankaccount.repository;

import br.com.dockbank.bankaccount.domain.entity.TransactionBank;
import br.com.dockbank.bankaccount.domain.request.AccountActivationRequest;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.JpaSpecificationExecutor;
import org.springframework.data.jpa.repository.Query;

public interface TransactionRepository extends JpaSpecificationExecutor<JpaRepository>, JpaRepository<TransactionBank, Long> {
//    List<TransactionBank> findTransactionBanksByAccountBank_AccountIdAndAccountBank_AccountActiveAndTransactionCreatedGreaterThanEqual(Long accountId, String status, LocalDate localDate);
List<TransactionBank> findTransactionBanksByAccountBank_AccountIdAndAccountBank_AccountActiveAndTransactionCreatedEquals(Long accountId, String status, LocalDate localDate);

}
