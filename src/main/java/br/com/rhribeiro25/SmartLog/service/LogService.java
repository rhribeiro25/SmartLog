package br.com.rhribeiro25.SmartLog.service;

import java.util.List;

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
	
	public List<LogModel> findByParams(String param);

	public boolean existsById(Long id);

}
