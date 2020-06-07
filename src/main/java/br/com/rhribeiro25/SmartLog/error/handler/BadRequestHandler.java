package br.com.rhribeiro25.SmartLog.error.handler;

import br.com.rhribeiro25.SmartLog.error.ErrorDetails;
import br.com.rhribeiro25.SmartLog.error.exception.BadRequestException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import javax.validation.ConstraintViolationException;
import java.util.Date;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */
@ControllerAdvice
public class BadRequestHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({
            UnsupportedOperationException.class,
            BadRequestException.class,
            ConstraintViolationException.class})
    public ResponseEntity<Object> handlerBadRequest(Exception ex) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .status(HttpStatus.BAD_REQUEST.name())
                .code(HttpStatus.BAD_REQUEST.value())
                .message(ex.getMessage())
                .timesTamp(new Date().getTime())
                .objectName(ex.getClass().getName())
                .build(),
                HttpStatus.BAD_REQUEST);
    }
}