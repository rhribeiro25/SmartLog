package br.com.rhribeiro25.SmartLog.error.handler;

import br.com.rhribeiro25.SmartLog.error.ErrorDetails;
import br.com.rhribeiro25.SmartLog.error.ValidationErrorDetails;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.MethodArgumentNotValidException;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;
import java.util.List;
import java.util.stream.Collectors;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */
@ControllerAdvice
public class MethodArgumentNotValidHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleMethodArgumentNotValid(MethodArgumentNotValidException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        List<ValidationErrorDetails> errors = getErrors(ex);
        ErrorDetails errorResponse = getErrorResponse(ex, status, errors);
        return new ResponseEntity<>(errorResponse, status);
    }

    private ErrorDetails getErrorResponse(MethodArgumentNotValidException ex, HttpStatus status, List<ValidationErrorDetails> errors) {
        return ErrorDetails.builder()
                .status(status.toString())
                .code(status.value())
                .message(ex.getMessage())
                .timesTamp(new Date().getTime())
                .objectName(ex.getBindingResult().getObjectName())
                .errors(errors)
                .build();
    }

    private List<ValidationErrorDetails> getErrors(MethodArgumentNotValidException ex) {
        return ex.getBindingResult().getFieldErrors().stream()
                .map(error -> ValidationErrorDetails.builder()
                        .message(error.getDefaultMessage())
                        .field(error.getField())
                        .parameter(error.getRejectedValue())
                        .build())
                .collect(Collectors.toList());
    }
}