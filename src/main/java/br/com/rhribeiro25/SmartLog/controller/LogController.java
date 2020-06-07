package br.com.rhribeiro25.SmartLog.controller;

import java.util.Date;
import java.util.List;

import javax.validation.Valid;

import org.springframework.batch.core.BatchStatus;
import org.springframework.batch.core.JobParametersInvalidException;
import org.springframework.batch.core.repository.JobExecutionAlreadyRunningException;
import org.springframework.batch.core.repository.JobInstanceAlreadyCompleteException;
import org.springframework.batch.core.repository.JobRestartException;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.boot.web.server.ErrorPage;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.security.access.prepost.PreAuthorize;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.multipart.MultipartFile;

import br.com.rhribeiro25.SmartLog.error.exception.NotFoundException;
import br.com.rhribeiro25.SmartLog.model.LogModel;
import br.com.rhribeiro25.SmartLog.service.LogService;
import br.com.rhribeiro25.SmartLog.utils.FilesIO;
import lombok.extern.slf4j.Slf4j;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */

@RestController
@RequestMapping("/logs")
@Slf4j
public class LogController {

	@Autowired
	private LogService logService;

	@Value("${files.csv.path}")
	private String csvFilesPath;

	@PostMapping("/create-by-batch")
	@PreAuthorize("hasRole('ADMIN')")
	@Transactional(rollbackFor = Exception.class)
	public ResponseEntity<Object> saveLogsFromFile(@RequestParam MultipartFile log) {

		Long now = System.currentTimeMillis();
		String logName = "log_" + now + ".csv";
		String logFullPath = csvFilesPath + logName;

		try {
			// Save File
			FilesIO.salvar(csvFilesPath, logName, log);

			// Run Job
			BatchStatus status = logService.runBatch(now, logFullPath);

			if (status == BatchStatus.COMPLETED)
				return new ResponseEntity<>(status.toString(), HttpStatus.CREATED);
			else if (status == BatchStatus.FAILED)
				return new ResponseEntity<>(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, status.toString()),
						HttpStatus.INTERNAL_SERVER_ERROR);
			else
				return new ResponseEntity<>(new ErrorPage(HttpStatus.BAD_REQUEST, status.toString()),
						HttpStatus.BAD_REQUEST);

		} catch (JobExecutionAlreadyRunningException | JobRestartException | JobInstanceAlreadyCompleteException
				| JobParametersInvalidException | RuntimeException e) {
			return new ResponseEntity<>(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}

	}

	@GetMapping("/find-by-id/{id}")
	public ResponseEntity<?> findById(@PathVariable("id") Long id) {
		LogModel log = this.returnExistsLog(id);
		return new ResponseEntity<>(log, HttpStatus.OK);
	}

	@GetMapping("/find-all")
	public ResponseEntity<?> findAll() {
		try {
			List<LogModel> logs = logService.findAll();
			this.verifyExistsLogs(logs);
			return new ResponseEntity<>(logs, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/find-by-ip/{ip}")
	public ResponseEntity<?> findByIp(@PathVariable("ip") String ip) {
		try {
			List<LogModel> logs = logService.findByIp(ip);
			this.verifyExistsLogs(logs);
			return new ResponseEntity<>(logs, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/find-by-status/{statusLog}")
	public ResponseEntity<?> findByStatus(@PathVariable("statusLog") Integer statusLog) {
		try {
			List<LogModel> logs = logService.findByStatus(statusLog);
			this.verifyExistsLogs(logs);
			return new ResponseEntity<>(logs, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@GetMapping("/find-by-createdat-between/{from}/{to}")
	public ResponseEntity<?> findByCreatedAtBetween(@PathVariable("from") String from,
			@PathVariable("to") String to) {
		try {
			List<LogModel> logs = logService.findByCreatedAtBetween(from, to);
			this.verifyExistsLogs(logs);
			return new ResponseEntity<>(logs, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping("/create")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> save(@RequestBody @Valid LogModel log) {
		try {
			log.setCreatedAt(new Date());
			logService.saveOrUpdate(log);
			return new ResponseEntity<>(log, HttpStatus.CREATED);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PutMapping("/update")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> update(@RequestBody LogModel log) {
		try {
			this.verifyExistsLog(log.getId());
			logService.saveOrUpdate(log);
			return new ResponseEntity<>(log, HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@DeleteMapping("/delete/{id}")
	@PreAuthorize("hasRole('ADMIN')")
	public ResponseEntity<?> delete(@PathVariable("id") String id) {
		try {
			this.verifyExistsLog(Long.parseLong(id));
			logService.delete(Long.parseLong(id));
			return new ResponseEntity<>("Successful to delet log!", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(new ErrorPage(HttpStatus.INTERNAL_SERVER_ERROR, e.getMessage()),
					HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	private LogModel returnExistsLog(Long id) {
		LogModel log = logService.findById(id);
		if (log == null)
			throw new NotFoundException("Log not found by ID: " + id);
		return log;
	}
	private void verifyExistsLog(Long id) {
		if (logService.findById(id) == null)
			throw new NotFoundException("Log not found by ID: " + id);
	}

	private void verifyExistsLogs(List<LogModel> logs) {
		if (logs == null || logs.size() == 0)
			throw new NotFoundException("Logs not found");
	}

}
