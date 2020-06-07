package br.com.rhribeiro25.SmartLog.error.exception;

import lombok.NoArgsConstructor;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */
@NoArgsConstructor
public class BadRequestException extends RuntimeException {

    private static final long serialVersionUID = -769147155483245030L;

    public BadRequestException(String msg) {
        super(msg);
    }

    public BadRequestException(String message, Throwable cause) {
        super(message, cause);
    }

    public BadRequestException(Throwable cause) {
        super(cause);
    }

}