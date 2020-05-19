package br.com.rhribeiro25.SmartLog.model;

import java.util.Date;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */
public class LogModel {
	private Date createdAt;
	private String ip;
	private String request;
	private Integer status;
	private String userAgent;

	public LogModel() {
	}

	public Date getCreatedAt() {
		return createdAt;
	}

	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public String getIp() {
		return ip;
	}

	public void setIp(String ip) {
		this.ip = ip;
	}

	public String getRequest() {
		return request;
	}

	public void setRequest(String request) {
		this.request = request;
	}

	public Integer getStatus() {
		return status;
	}

	public void setStatus(Integer status) {
		this.status = status;
	}

	public String getUserAgent() {
		return userAgent;
	}

	public void setUserAgent(String userAgent) {
		this.userAgent = userAgent;
	}

}
