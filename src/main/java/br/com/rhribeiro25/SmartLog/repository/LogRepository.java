package br.com.rhribeiro25.SmartLog.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import br.com.rhribeiro25.SmartLog.model.LogModel;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */

public interface LogRepository extends JpaRepository<LogModel, Long> {

	public List<LogModel> findLogModelsByIpIsContainingOrRequestIsContainingOrUserAgentIsContaining(String ip, String request, String userAgent);
	
	public List<LogModel> findLogModelsByCreatedAtBetween(Date from, Date to);

}
