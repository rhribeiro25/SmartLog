package br.com.rhribeiro25.SmartLog.repository;

import java.util.Date;
import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rhribeiro25.SmartLog.model.LogModel;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */

public interface LogRepository extends JpaRepository<LogModel, Long> {

	public List<LogModel> findLogModelsByIpIsContaining(String ip);
	
	public List<LogModel> findLogModelsByStatus(Integer status);
	
	public List<LogModel> findLogModelsByCreatedAtBetween(Date from, Date to);

}
