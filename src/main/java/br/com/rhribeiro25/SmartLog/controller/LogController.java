package br.com.rhribeiro25.SmartLog.controller;

import java.io.IOException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import br.com.rhribeiro25.SmartLog.model.LogModel;
import br.com.rhribeiro25.SmartLog.service.LogService;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */

@RestController
@RequestMapping("/logs")
public class LogController {

	@Autowired
	private LogService logService;

	@GetMapping("/")
	public ResponseEntity<String> findBestPrice(@PathVariable("from-to") String from_to) throws IOException {
		try {
			return new ResponseEntity<>("", HttpStatus.OK);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}

	@PostMapping
	public ResponseEntity<String> saveRoute(@RequestBody LogModel route) throws IOException {
		try {
			return new ResponseEntity<>("", HttpStatus.BAD_REQUEST);
		} catch (Exception e) {
			return new ResponseEntity<>(e.getMessage(), HttpStatus.INTERNAL_SERVER_ERROR);
		}
	}
}
