package br.com.dockbank.bankaccount.controller;

import static org.springframework.http.MediaType.APPLICATION_JSON_VALUE;

import br.com.dockbank.bankaccount.domain.request.TransactionRequest;
import br.com.dockbank.bankaccount.domain.response.TransactionResponse;
import br.com.dockbank.bankaccount.service.TransactionServiceImpl;
import io.swagger.annotations.Api;
import io.swagger.annotations.ApiOperation;
import io.swagger.annotations.ApiResponse;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;


@RestController
@CrossOrigin
@Api(tags = "Financial transactions")
@RequestMapping(value = "v1/")
public class TransactionController {

    private final String DEPOSIT = "deposits";
    private final String WITHDRAW = "withdraws";

    @Autowired
    private TransactionServiceImpl service;

    @PostMapping(value = DEPOSIT,
        produces = APPLICATION_JSON_VALUE,
        consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a new Transaction")
    @ApiResponse(code = 201, message = "Success")
    public ResponseEntity<TransactionResponse> deposit(
        final @RequestBody TransactionRequest request) {

        final TransactionResponse response = service.deposit(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }

    @PostMapping(value = WITHDRAW,
        produces = APPLICATION_JSON_VALUE,
        consumes = APPLICATION_JSON_VALUE)
    @ApiOperation(value = "Create a new Transaction")
    @ApiResponse(code = 201, message = "Success")
    public ResponseEntity<TransactionResponse> withdraw(
        final @RequestBody TransactionRequest request) {

        final TransactionResponse response = service.withdraw(request);
        return new ResponseEntity<>(response, HttpStatus.CREATED);
    }
}
