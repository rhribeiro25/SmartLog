package br.com.rhribeiro25.SmartLog.error;

import lombok.Builder;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */

@Builder
public class ValidationErrorDetails {
    private final String message;
    private final String field;
    private final Object parameter;
}
