package br.com.rhribeiro25.SmartLog.error.handler;

import br.com.rhribeiro25.SmartLog.error.ErrorDetails;
import br.com.rhribeiro25.SmartLog.error.exception.UnauthorizedException;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.ControllerAdvice;
import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.servlet.mvc.method.annotation.ResponseEntityExceptionHandler;

import java.util.Date;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */
@ControllerAdvice
public class UnauthorizedHandler extends ResponseEntityExceptionHandler {

    @ExceptionHandler({UnauthorizedException.class})
    public ResponseEntity<?> handlerUnauthorized(Exception ex) {
        return new ResponseEntity<>(ErrorDetails.builder()
                .status(HttpStatus.UNAUTHORIZED.name())
                .code(HttpStatus.UNAUTHORIZED.value())
                .message(ex.getMessage())
                .timesTamp(new Date().getTime())
                .objectName(ex.getClass().getName())
                .build(),
                HttpStatus.UNAUTHORIZED);
    }
}
