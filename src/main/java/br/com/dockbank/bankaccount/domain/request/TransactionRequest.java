package br.com.dockbank.bankaccount.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Transaction Request")
public class TransactionRequest {
    @NotNull
    @ApiModelProperty(value = "Account Id", required = true)
    private Long accountId;

    @ApiModelProperty(value = "Transaction value", required = true)
    private BigDecimal transactionValue;

    public TransactionRequest(@NotNull Long accountId) {
        this.accountId = accountId;
    }

    public TransactionRequest(@NotNull Long accountId,
        BigDecimal transactionValue) {
        this.accountId = accountId;
        this.transactionValue = transactionValue;
    }

    public Long getAccountId() {
        return accountId;
    }

    public void setAccountId(Long accountId) {
        this.accountId = accountId;
    }

    public BigDecimal getTransactionValue() {
        return transactionValue;
    }

    public void setTransactionValue(BigDecimal transactionValue) {
        this.transactionValue = transactionValue;
    }
}
