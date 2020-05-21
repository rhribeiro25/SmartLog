package br.com.rhribeiro25.SmartLog.service;

import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rhribeiro25.SmartLog.model.LogModel;
import br.com.rhribeiro25.SmartLog.repository.LogRepository;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */

@Service
public class LogServiceImpl implements LogService {

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
	public LogModel save(LogModel logModel) {
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

}
