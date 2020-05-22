package br.com.rhribeiro25.SmartLog.service;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import br.com.rhribeiro25.SmartLog.model.LogModel;
import br.com.rhribeiro25.SmartLog.repository.LogRepository;
import br.com.rhribeiro25.SmartLog.utils.Formatting;

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
	public List<LogModel> findByParams(String param) {
		return logRepository.findLogModelsByIpIsContainingOrRequestIsContainingOrUserAgentIsContaining(param, param, param);
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
	public LogModel delete(LogModel logModel) {
		logRepository.delete(logModel);
		return logModel;
	}
	
	@Override
	public boolean existsById(Long id) {
		return logRepository.existsById(id);
	}

}
