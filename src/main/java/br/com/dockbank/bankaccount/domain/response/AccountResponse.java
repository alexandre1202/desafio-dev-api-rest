package br.com.dockbank.bankaccount.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDate;

@ApiModel(description = "Account information's")
public class AccountResponse {

    @ApiModelProperty(value = "Account Id", name = "accountId", required = true)
    private Long accountId;

    @ApiModelProperty(value = "Customer Id", name = "customerId", required = true)
    private Long customerId;

    @ApiModelProperty(value = "Account Balance", name = "balance")
    private BigDecimal balance;

    @ApiModelProperty(value = "Account withdraw limit per day", name = "withdrawLimitPerDay")
    private BigDecimal withdrawLimitPerDay;

    @ApiModelProperty(value = "Account active status", name = "accountActive")
    private String accountActive;

    @ApiModelProperty(value = "Account type", name = "accountType")
    private int accountType;

    @ApiModelProperty(value = "Account date created", name = "accountCreated")
    private LocalDate accountCreated;

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public Long getCustomerId() {
        return customerId;
    }

    public void setCustomerId(Long customerId) {
        this.customerId = customerId;
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
}
