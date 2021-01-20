package br.com.dockbank.bankaccount.common.exception;

import com.fasterxml.jackson.annotation.JsonFormat;
import com.fasterxml.jackson.annotation.JsonFormat.Shape;
import java.time.LocalDateTime;
import javax.servlet.http.HttpServletRequest;
import org.springframework.core.annotation.AnnotationUtils;
import org.springframework.core.annotation.Order;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.ResponseStatus;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

@ControllerAdvice
@Order
public class RestExceptionHandler extends ResponseEntityExceptionHandler {

    @JsonFormat(shape = Shape.STRING, pattern = "dd-MM-yyyy hh:mm:ss")
    private LocalDateTime timestamp = LocalDateTime.now();

    @ResponseBody
    @ExceptionHandler(UnprocessableEntityException.class)
    public ResponseEntity<Object> handleUnprocessableEntityException(HttpServletRequest req, UnprocessableEntityException e) {
        HttpStatus status = getExceptionResponseStatusOr(e, HttpStatus.UNPROCESSABLE_ENTITY);
        return buildExceptionResponse(status, req, e);
    }

    private HttpStatus getExceptionResponseStatusOr(Exception e, HttpStatus or) {
        ResponseStatus annotation = AnnotationUtils.findAnnotation(e.getClass(), ResponseStatus.class);
        return (annotation != null) ? annotation.value() : or;
    }

    private ResponseEntity<Object> buildExceptionResponse(HttpStatus status, HttpServletRequest req, Exception e) {
        return buildExceptionResponse(status, req, e, e.getMessage());
    }

    private ResponseEntity<Object> buildExceptionResponse(HttpStatus status, HttpServletRequest req, Exception e,
        String errorMessage) {
        logger.error("An error has occured ", e);

        RestExceptionMessage response = new RestExceptionMessage(req.getRequestURI(), req.getQueryString(),
            errorMessage, timestamp, status);

        return new ResponseEntity<>(response, status);
    }

}