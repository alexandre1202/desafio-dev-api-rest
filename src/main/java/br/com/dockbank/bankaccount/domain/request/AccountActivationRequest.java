package br.com.dockbank.bankaccount.domain.request;

import io.swagger.annotations.ApiModel;
import io.swagger.annotations.ApiModelProperty;
import javax.validation.constraints.NotNull;

@ApiModel(description = "Activation Account status")
public class AccountActivationRequest {

    @ApiModelProperty(value = "Activate Account status value", required = true, allowableValues = "A,D")
    @NotNull
    private String status;

    public String getStatus() {
        return status;
    }

    public void setStatus(String status) {
        this.status = status;
    }
}
