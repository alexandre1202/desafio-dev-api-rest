package br.com.dockbank.bankaccount.controller;

import br.com.dockbank.bankaccount.domain.request.TransactionRequest;
import br.com.dockbank.bankaccount.domain.response.TransactionResponse;
import br.com.dockbank.bankaccount.service.TransactionService;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.validation.annotation.Validated;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;


@RestController
@CrossOrigin
@Validated
@Api(tags = "Financial transactions")
@RequestMapping(value = "v1")
public class TransactionController {

    private final String DEPOSIT = "/deposit";
    private final String WITHDRAW = "/withdraw";

    @Autowired
    private TransactionService service;

    @PostMapping(path = DEPOSIT,
        consumes = APPLICATION_JSON_VALUE,
        produces = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a new Transaction")
    @ApiResponse(code = 201, message = "Success")
    public ResponseEntity<TransactionResponse> deposit(
        @RequestBody TransactionRequest request) {

        TransactionResponse response = service.deposit(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(path = WITHDRAW,
        consumes = "application/json",
        produces = "application/json")
    @ApiOperation(value = "Create a new Transaction")
    @ApiResponse(code = 200, message = "Success")
    public ResponseEntity<TransactionResponse> withdraw(
        @RequestBody TransactionRequest request) {

        TransactionResponse response = service.withdraw(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
