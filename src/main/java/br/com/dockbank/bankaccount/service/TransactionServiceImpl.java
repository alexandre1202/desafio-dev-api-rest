package br.com.dockbank.bankaccount.service;

import br.com.dockbank.bankaccount.common.exception.UnprocessableEntityException;
import br.com.dockbank.bankaccount.domain.entity.AccountBank;
import br.com.dockbank.bankaccount.domain.entity.TransactionBank;
import br.com.dockbank.bankaccount.domain.mapper.TransactionMapper;
import br.com.dockbank.bankaccount.domain.request.TransactionRequest;
import br.com.dockbank.bankaccount.domain.response.TransactionResponse;
import br.com.dockbank.bankaccount.repository.AccountRepository;
import br.com.dockbank.bankaccount.repository.TransactionRepository;
import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.List;
import java.util.Optional;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

@Service
@Transactional("transactionManager")
public class TransactionServiceImpl implements TransactionService {

    @Autowired
    private TransactionRepository repository;

    @Autowired
    private AccountRepository accountRepository;

    @Autowired
    private TransactionMapper mapper;

    @Override
    public TransactionResponse deposit(TransactionRequest request) {
        AccountBank accountBank = getAccount(request);
        depositValidation(request);

        accountBank.setBalance(request.getTransactionValue().add(accountBank.getBalance()));
        accountRepository.save(accountBank);

        TransactionBank transactionBank = new TransactionBank(accountBank, request.getTransactionValue(),
            LocalDate.now());

        return mapper.toResponse(repository.save(transactionBank));
    }

    private AccountBank getAccount(TransactionRequest request) {
        Optional<AccountBank> accountBankOptional = accountRepository.findById(request.getAccountId());
        if (! accountBankOptional.isPresent()) {
            throw new UnprocessableEntityException("Account id not found");
        }
        if (! "A".equals(accountBankOptional.get().getAccountActive())) {
            throw new UnprocessableEntityException("Account invalid");
        }
        return accountBankOptional.get();
    }

    private void depositValidation(TransactionRequest request) {
        if (request.getTransactionValue().doubleValue() <= 0) {
            throw new UnprocessableEntityException("Deposit can not be negative or zero");
        }
    }

    @Override
    public TransactionResponse withdraw(TransactionRequest request) {
        AccountBank accountBank = getAccount(request);
        withdrawValidation(request, accountBank);
        accountBank.setBalance(accountBank.getBalance().subtract(request.getTransactionValue()));
        accountRepository.save(accountBank);

        TransactionBank transactionBank = new TransactionBank(accountBank, request.getTransactionValue().multiply(new BigDecimal(-1)), LocalDate.now());
        transactionBank = repository.save(transactionBank);

        return mapper.toResponse(transactionBank);
    }

    private void withdrawValidation(TransactionRequest request, AccountBank accountBank) {
        List<TransactionBank> transactionBankList = repository
            .findTransactionBanksByAccountBank_AccountIdAndAccountBank_AccountActiveAndTransactionCreatedEquals(
                accountBank.getAccountId(),
                "A",
                LocalDate.now());
        Double totalTransactionValuePerDay = transactionBankList.stream()
            .map(t -> t.getTransactionValue().doubleValue()).reduce(Double::sum).orElse(0D);

        if ((request.getTransactionValue().add(BigDecimal.valueOf(totalTransactionValuePerDay))).compareTo(accountBank.getWithdrawLimitPerDay()) > 0) {
            throw new UnprocessableEntityException("Withdraw limit per day has exceeded");
        }

        if (request.getTransactionValue().compareTo(accountBank.getBalance()) > 0) {
            throw new UnprocessableEntityException("Insufficient balance");
        }
    }

}
