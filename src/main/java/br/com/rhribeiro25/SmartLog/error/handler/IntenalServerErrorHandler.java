package br.com.rhribeiro25.SmartLog.error.handler;

import br.com.rhribeiro25.SmartLog.error.ErrorDetails;
import org.springframework.beans.ConversionNotSupportedException;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.http.converter.HttpMessageNotWritableException;
import org.springframework.web.bind.MissingPathVariableException;
import org.springframework.web.bind.annotation.RestControllerAdvice;
import org.springframework.web.context.request.WebRequest;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */

@RestControllerAdvice
public class IntenalServerErrorHandler extends ResponseEntityExceptionHandler {

    @Override
    public ResponseEntity<Object> handleMissingPathVariable(MissingPathVariableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .status(status.name())
                .code(status.value())
                .message(ex.getMessage())
                .timesTamp(new Date().getTime())
                .objectName(ex.getClass().getName())
                .build(),
                status);
    }

    @Override
    public ResponseEntity<Object> handleConversionNotSupported(ConversionNotSupportedException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .status(status.name())
                .code(status.value())
                .message(ex.getMessage())
                .timesTamp(new Date().getTime())
                .objectName(ex.getClass().getName())
                .build(),
                status);
    }

    @Override
    public ResponseEntity<Object> handleHttpMessageNotWritable(HttpMessageNotWritableException ex, HttpHeaders headers, HttpStatus status, WebRequest request) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .status(status.name())
                .code(status.value())
                .message(ex.getMessage())
                .timesTamp(new Date().getTime())
                .objectName(ex.getClass().getName())
                .build(),
                status);
    }
}
