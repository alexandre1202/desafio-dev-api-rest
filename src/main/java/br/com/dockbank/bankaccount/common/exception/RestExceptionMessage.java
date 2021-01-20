package br.com.dockbank.bankaccount.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;
import lombok.Getter;
import lombok.Setter;
import org.springframework.http.HttpStatus;

@Getter
@Setter
public class RestExceptionMessage {

    @JsonFormat(shape = Shape.STRING, pattern = "dd-MMM-yyyy hh:mm:ss")
    private LocalDateTime timeStamp = LocalDateTime.now();
    private String requestURI;
    private String queryString;
    private Integer returnCode;
    private HttpStatus httpStatus;
    private String customMessage;

    public RestExceptionMessage(String requestURI, String queryString, String message, LocalDateTime timeStamp, HttpStatus httpStatus) {
        super();
        this.requestURI = requestURI;
        this.queryString = queryString;
        this.customMessage = message;
        this.timeStamp = timeStamp;
        this.httpStatus = httpStatus;
        this.returnCode = httpStatus.value();
    }
}