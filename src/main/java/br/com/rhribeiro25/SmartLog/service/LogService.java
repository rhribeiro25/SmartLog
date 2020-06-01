package br.com.rhribeiro25.SmartLog.service;

import java.util.List;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;

import br.com.rhribeiro25.SmartLog.model.LogModel;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */
public interface LogService {

	public List<LogModel> findAll();
	
	public LogModel findById(Long id);
	
	public LogModel saveOrUpdate(LogModel logModel);
	
	public void delete(Long id);

	public List<LogModel> saveAll(List<? extends LogModel> logs);

	public List<LogModel> findByCreatedAtBetween(String from, String to);
	
	public boolean existsById(Long id);

	public List<LogModel> findByIp(String ip);

	public BatchStatus runBatch(Long now, String logFullPath) throws JobExecutionAlreadyRunningException, JobRestartException,
			JobInstanceAlreadyCompleteException, JobParametersInvalidException;

	public List<LogModel> findByStatus(Integer status);

}
