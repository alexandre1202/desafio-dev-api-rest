package br.com.dockbank.bankaccount.domain.response;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import java.math.BigDecimal;
import java.time.LocalDate;

@ApiModel(description = "Transaction response")
public class TransactionResponse {

    @ApiModelProperty(value = "Transaction Id", name = "transactionId", required = true)
    private Long transactionId;

    @ApiModelProperty(value = "Account Id", name = "accountId", required = true)
    private Long accountId;

    @ApiModelProperty(value = "Transaction Value", name = "transactionValue")
    private BigDecimal transactionValue;

    @ApiModelProperty(value = "Transaction date created", name = "transactionCreated")
    private LocalDate transactionCreated;

    public Long getTransactionId() {
        return transactionId;
    }

    public void setTransactionId(Long transactionId) {
        this.transactionId = transactionId;
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

    public LocalDate getTransactionCreated() {
        return transactionCreated;
    }

    public void setTransactionCreated(LocalDate transactionCreated) {
        this.transactionCreated = transactionCreated;
    }
}
