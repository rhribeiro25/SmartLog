package br.com.rhribeiro25.SmartLog.model.dto;

import java.util.Date;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.validation.constraints.NotBlank;
import javax.validation.constraints.NotNull;
import javax.validation.constraints.Size;

import br.com.rhribeiro25.SmartLog.model.LogModel;
import lombok.Getter;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */

@Getter
public class LogDTO {

	private Long id;

	@NotNull(message = "CreatedAt cannot be null!")
	private Date createdAt;

	@NotBlank(message = "IP cannot be null!")
	@Size(min = 9, max = 15, message = "IP must be between 9 and 15 characters!")
	private String ip;

	@NotBlank(message = "Request cannot be null!")
	@Size(min = 12, max = 48, message = "Request must be between 12 and 48 characters!")
	private String request;

	@NotNull(message = "Status cannot be null!")
	@Size(min = 100, max = 511, message = "Status should not be less than 100 and greater than 511!")
	private Integer status;

	@NotBlank(message = "User Agent cannot be null!")
	@Size(min = 24, max = 256, message = "User Agent must be between 24 and 256 characters!")
	private String userAgent;

	 public LogModel convertToLog(){
	        return new LogModel(id, createdAt, ip, request, status, userAgent);
	    }

}
