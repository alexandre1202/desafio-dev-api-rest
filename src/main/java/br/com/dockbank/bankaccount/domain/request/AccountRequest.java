package br.com.dockbank.bankaccount.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Account Request")
public class AccountRequest {

    @NotNull
    @ApiModelProperty(value = "Customer Id", required = true)
    private Long customerId;

    @ApiModelProperty(value = "Account balance with decimal format 9.99")
    private BigDecimal balance;

    @ApiModelProperty(value = "withdraw limit per day with decimal format 9.99")
    private BigDecimal withdrawLimitPerDay;

    @ApiModelProperty(value = "Account active indicator", allowableValues = "A,D")
    private String accountActive;

    @ApiModelProperty(value = "Account type", allowableValues = "1,2", example = "1 - Personal, 2 - Juridic Entity")
    private Integer accountType;

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

    public Integer getAccountType() {
        return accountType;
    }

    public void setAccountType(Integer accountType) {
        this.accountType = accountType;
    }
}
