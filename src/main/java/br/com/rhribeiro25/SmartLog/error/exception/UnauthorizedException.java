package br.com.rhribeiro25.SmartLog.error.exception;

import lombok.NoArgsConstructor;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */
@NoArgsConstructor
public class UnauthorizedException extends RuntimeException {

    private static final long serialVersionUID = -769147155483245010L;

    public UnauthorizedException(String msg) {
        super(msg);
    }

    public UnauthorizedException(String message, Throwable cause) {
        super(message, cause);
    }

    public UnauthorizedException(Throwable cause) {
        super(cause);
    }

}