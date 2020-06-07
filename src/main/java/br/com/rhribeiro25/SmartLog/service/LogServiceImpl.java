package br.com.rhribeiro25.SmartLog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.Job;
import org.springframework.batch.core.JobExecution;
import org.springframework.batch.core.JobParameter;
import org.springframework.batch.core.JobParameters;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.launch.JobLauncher;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rhribeiro25.SmartLog.model.LogModel;
import br.com.rhribeiro25.SmartLog.repository.LogRepository;
import br.com.rhribeiro25.SmartLog.utils.Formatting;
import lombok.extern.slf4j.Slf4j;


/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */

@Service
@Slf4j
public class LogServiceImpl implements LogService {

	@Autowired
	JobLauncher jobLauncher;
	
	@Autowired
	Job job;
	
	@Autowired
	private LogRepository logRepository;

	@Override
	public List<LogModel> findAll() {
		List<LogModel> products = new ArrayList<>();
		logRepository.findAll().forEach(products::add);
		return products;
	}

	@Override
	public LogModel findById(Long id) {
		return logRepository.findById(id).orElse(null);
	}

	@Override
	public List<LogModel> findByIp(String ip) {
		return logRepository.findLogModelsByIpIsContaining(ip);
	}
	
	@Override
	public List<LogModel> findByStatus(Integer status) {
		return logRepository.findLogModelsByStatus(status);
	}

	@Override
	public List<LogModel> findByCreatedAtBetween(String from, String to) {
		Date dateFrom = Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss(from);
		Date dateTo = Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss(to);
		return logRepository.findLogModelsByCreatedAtBetween(dateFrom, dateTo);
	}

	@Override
	public LogModel saveOrUpdate(LogModel logModel) {
		logRepository.save(logModel);
		return logModel;
	}

	@Override
	public List<LogModel> saveAll(List<? extends LogModel> logs) {
		logRepository.saveAll(logs);
		return (List<LogModel>) logs;
	}

	@Override
	public void delete(Long id) {
		logRepository.deleteById(id);
	}
	
	@Override
	public boolean existsById(Long id) {
		return logRepository.existsById(id);
	}
	
	@Override
	public BatchStatus runBatch(Long now, String logFullPath) throws JobExecutionAlreadyRunningException, JobRestartException, JobInstanceAlreadyCompleteException, JobParametersInvalidException {
		Map<String, JobParameter> maps = new HashMap<>();
		maps.put("time", new JobParameter(now));
		maps.put("filePath", new JobParameter(logFullPath));
		JobParameters parameters = new JobParameters(maps);
		JobExecution jobExecution = jobLauncher.run(job, parameters);
		BatchStatus status = jobExecution.getStatus();
		return status;
	}

}
