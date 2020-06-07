package br.com.rhribeiro25.SmartLog.error;

import lombok.Builder;

import java.util.List;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */

@Builder
public class ErrorDetails {
	private final String status;
	private final int code;
	private final String message;
	private final Long timesTamp;
	private final String objectName;
	private final List<ValidationErrorDetails> errors;
}

