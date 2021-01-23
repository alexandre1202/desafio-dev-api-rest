package br.com.dockbank.bankaccount.domain.entity;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.util.Objects;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.SequenceGenerator;
import javax.persistence.Table;
import org.hibernate.annotations.DynamicInsert;
import org.hibernate.annotations.DynamicUpdate;

@DynamicInsert
@DynamicUpdate
@Entity
@Table(name = "TRANSACTIONS")
public class TransactionBank {
    @Id
    @Column(name = "TRANSACTION_ID")
    @SequenceGenerator(name = "TRANSACTIONS_GENERATOR", sequenceName = "SQ_TRANSACTIONS")
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long transactionId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "account_id", referencedColumnName = "account_id")
    private AccountBank accountBank;

    @Column
    private BigDecimal transactionValue;

    @Column
    private LocalDate transactionCreated;

    public TransactionBank() {}

    public TransactionBank(AccountBank accountBank,
        BigDecimal transactionValue,
        LocalDate transactionCreated) {
        this.accountBank = accountBank;
        this.transactionValue = transactionValue;
        this.transactionCreated = transactionCreated;
    }

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
    }

    public AccountBank getAccountBank() {
        return accountBank;
    }

    public void setAccountBank(AccountBank accountBank) {
        this.accountBank = accountBank;
    }

    public BigDecimal getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(BigDecimal transactionValue) {
        this.transactionValue = transactionValue;
    }

    public LocalDate getTransactionCreated() {
        return transactionCreated;
    }

    public void setTransactionCreated(LocalDate transactionCreated) {
        this.transactionCreated = transactionCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        TransactionBank that = (TransactionBank) o;
        return Objects.equals(transactionId, that.transactionId) && Objects
            .equals(accountBank, that.accountBank) && Objects
            .equals(transactionValue, that.transactionValue) && Objects
            .equals(transactionCreated, that.transactionCreated);
    }

    @Override
    public String toString() {
        return "TransactionBank{" +
            "transactionId=" + transactionId +
            ", accountBank=" + accountBank +
            ", transactionValue=" + transactionValue +
            ", transactionCreated=" + transactionCreated +
            '}';
    }
}
