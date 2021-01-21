package br.com.dockbank.bankaccount.controller;

import br.com.dockbank.bankaccount.domain.request.AccountActivationRequest;
import br.com.dockbank.bankaccount.domain.request.AccountRequest;
import br.com.dockbank.bankaccount.domain.response.AccountResponse;
import br.com.dockbank.bankaccount.service.AccountService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import javax.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@CrossOrigin
@Validated
@Api(tags = "Account balance")
@RequestMapping(value = "v1")
public class AccountController {

    private final String ACCOUNT = "/account";
    private final String ACCOUNT_BALANCE = ACCOUNT + "/{accountId}";
    private final String ACCOUNT_ACTIVATION = ACCOUNT + "/{accountId}/activation";

    @Autowired
    private AccountService service;

    @GetMapping(path = ACCOUNT_BALANCE)
    @ApiOperation(value = "Account information")
    @ApiResponse(code = 200, message = "Success")
    public ResponseEntity<AccountResponse> getAccountBalance(
        @PathVariable Long accountId) {

        AccountResponse response = service.getAccountBalance(accountId);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PutMapping(path = ACCOUNT_ACTIVATION,
        consumes = "application/json",
        produces = "application/json")
    @ApiOperation(value = "Blocking Account")
    @ApiResponse(code = 200, message = "Updated")
    public ResponseEntity<AccountResponse> blockAccount(
        @PathVariable Long accountId,
        @RequestBody @Valid AccountActivationRequest status) {

        AccountResponse response = service.activationAccount(accountId, status);
        return new ResponseEntity<>(response, HttpStatus.OK);
    }

    @PostMapping(path = ACCOUNT,
        consumes = "application/json",
        produces = "application/json")
    @ApiOperation(value = "Create a new Account")
    @ApiResponse(code = 201, message = "Created")
    public ResponseEntity<AccountResponse> createAccount(
        @RequestBody AccountRequest request) {

        AccountResponse response = service.createAccount(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
