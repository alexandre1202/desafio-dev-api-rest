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
@Table(name = "ACCOUNT")
public class AccountBank {

    @Id
    @Column(name = "ACCOUNT_ID")
    @SequenceGenerator(name = "ACCOUNT_GENERATOR", sequenceName = "SQ_ACCOUNT", allocationSize = 1)
    @GeneratedValue(strategy = GenerationType.AUTO)
    private Long accountId;

    @ManyToOne(fetch = FetchType.LAZY)
    @JoinColumn(name = "customer_id", referencedColumnName = "customer_id")
    private CustomerBank customerBank;

    @Column
    private BigDecimal balance;

    @Column
    private BigDecimal withdrawLimitPerDay;

    @Column
    private String accountActive;

    @Column
    private int accountType;

    @Column
    private LocalDate accountCreated;

    public AccountBank() {}

    public AccountBank(CustomerBank customerBank,
        BigDecimal balance,
        BigDecimal withdrawLimitPerDay,
        String accountActive,
        int accountType,
        LocalDate accountCreated) {
        this.customerBank = customerBank;
        this.balance = balance;
        this.withdrawLimitPerDay = withdrawLimitPerDay;
        this.accountActive = accountActive;
        this.accountType = accountType;
        this.accountCreated = accountCreated;
    }

    public CustomerBank getCustomerBank() {
        return customerBank;
    }

    public void setCustomerBank(CustomerBank customerBank) {
        this.customerBank = customerBank;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getBalance() {
        return balance;
    }

    public void setBalance(BigDecimal balance) {
        this.balance = balance;
    }

    public BigDecimal getWithdrawLimitPerDay() {
        return withdrawLimitPerDay;
    }

    public void setWithdrawLimitPerDay(BigDecimal withdrawLimitPerDay) {
        this.withdrawLimitPerDay = withdrawLimitPerDay;
    }

    public String getAccountActive() {
        return accountActive;
    }

    public void setAccountActive(String accountActive) {
        this.accountActive = accountActive;
    }

    public int getAccountType() {
        return accountType;
    }

    public void setAccountType(int accountType) {
        this.accountType = accountType;
    }

    public LocalDate getAccountCreated() {
        return accountCreated;
    }

    public void setAccountCreated(LocalDate accountCreated) {
        this.accountCreated = accountCreated;
    }

    @Override
    public boolean equals(Object o) {
        if (this == o) {
            return true;
        }
        if (o == null || getClass() != o.getClass()) {
            return false;
        }
        AccountBank that = (AccountBank) o;
        return //accountActive == that.accountActive &&
         accountType == that.accountType
            && Objects.equals(accountId, that.accountId) && Objects
            .equals(customerBank, that.customerBank) && Objects.equals(balance, that.balance)
            && Objects.equals(withdrawLimitPerDay, that.withdrawLimitPerDay)
            && Objects.equals(accountCreated, that.accountCreated);
    }

    @Override
    public String toString() {
        return "AccountBank{" +
            "accountId=" + accountId +
            ", customerBank=" + customerBank +
            ", balance=" + balance +
            ", withdrawLimitPerDay=" + withdrawLimitPerDay +
            ", accountActive=" + accountActive +
            ", accountType=" + accountType +
            ", accountCreated=" + accountCreated +
            '}';
    }
}
