package br.com.rhribeiro25.SmartLog.controller;

import java.lang.reflect.Array;
import java.util.ArrayList;
import java.util.Collections;
import java.util.Date;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.Optional;

import org.assertj.core.api.Assertions;
import org.assertj.core.util.Arrays;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.BDDMockito;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.context.TestConfiguration;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.boot.test.web.client.TestRestTemplate;
import org.springframework.boot.web.client.RestTemplateBuilder;
import org.springframework.boot.web.server.LocalServerPort;
import org.springframework.context.annotation.Bean;
import org.springframework.http.HttpEntity;
import org.springframework.http.HttpHeaders;
import org.springframework.http.HttpMethod;
import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.util.LinkedMultiValueMap;
import org.springframework.util.MultiValueMap;

import br.com.rhribeiro25.SmartLog.model.LogModel;
import br.com.rhribeiro25.SmartLog.repository.LogRepository;
import br.com.rhribeiro25.SmartLog.utils.Formatting;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = SpringBootTest.WebEnvironment.RANDOM_PORT)
public class LogControllerTests {

	@Autowired
	private TestRestTemplate restTemplate;

	@LocalServerPort
	private int port;

	@MockBean
	private LogRepository logRepository;
	
	private List<LogModel> logs;
	private LogModel logModel;
	private Date from, to;
	private HttpEntity<LogModel> request;

	@TestConfiguration
	static class SpringBootTestConfig {
		@Bean
		public RestTemplateBuilder restTemplateBuilder() {
			return new RestTemplateBuilder().basicAuthentication("user", "smartLog2020");
		}
	}
	
	@Before
	public void setup() {
		logs = new ArrayList<LogModel>();
		logs.add(new LogModel(1L, Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-01 00:00:23.003"), "192.168.169.191", "GET / HTTP/1.1", 200, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393"));
		logs.add(new LogModel(2L, Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-02 00:00:23.003"), "192.168.169.192", "GET / HTTP/1.1", 201, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393"));
		logs.add(new LogModel(3L, Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-03 00:00:23.003"), "192.168.169.193", "GET / HTTP/1.1", 400, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393"));
		logs.add(new LogModel(4L, Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-04 00:00:23.003"), "192.168.169.194", "GET / HTTP/1.1", 404, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393"));
		logs.add(new LogModel(5L, Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-05 00:00:23.003"), "192.168.169.195", "GET / HTTP/1.1", 500, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393"));
		logs.add(new LogModel(6L, Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-06 00:00:23.003"), "192.168.169.196", "GET / HTTP/1.1", 200, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393"));
		logs.add(new LogModel(7L, Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-07 00:00:23.003"), "192.168.169.197", "GET / HTTP/1.1", 201, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393"));
		logs.add(new LogModel(8L, Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-08 00:00:23.003"), "192.168.169.198", "GET / HTTP/1.1", 400, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393"));
		logs.add(new LogModel(9L, Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-09 00:00:23.003"), "192.168.169.199", "GET / HTTP/1.1", 404, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393"));
		logs.add(new LogModel(10L, Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-10 00:00:23.003"), "192.168.169.200", "GET / HTTP/1.1", 500, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393"));
		logModel = new LogModel(11L, Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-01 00:00:23.003"), "192.168.169.194", "GET / HTTP/1.1", 200, "Mozilla/5.0 (Windows NT 10.0; Win64; x64) AppleWebKit/537.36 (KHTML, like Gecko) Chrome/51.0.2704.79 Safari/537.36 Edge/14.14393");
		
		from = Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-01 00:00:23.003");
		to = Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss("2020-01-05 00:00:23.003");
		
		request = new HttpEntity<>(logModel);
		
	}

	/**
	 * FindById
	 */
	@Test
	public void findByIdHttpStatus200() {
		BDDMockito.when(logRepository.findById(11L)).thenReturn(Optional.of(logModel));
		ResponseEntity<LogModel> response = restTemplate.getForEntity("/logs/find-by-id/11", LogModel.class);
		Assertions.assertThat(response.getBody().getUserAgent()).isEqualTo(logModel.getUserAgent());
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void findByIdHttpStatus401() {
		restTemplate = restTemplate.withBasicAuth("test", "test");
		BDDMockito.when(logRepository.findById(11L)).thenReturn(Optional.of(logModel));
		ResponseEntity<String> response = restTemplate.getForEntity("/logs/find-by-id/11", String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(401);
	}
	
	@Test
	public void findByIdHttpStatus404() {
		BDDMockito.when(logRepository.findById(11L)).thenReturn(Optional.of(logModel));
		ResponseEntity<String> response = restTemplate.getForEntity("/logs/find-by-id/2", String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}
	
	@Test
	public void findByIdHttpStatus400() {
		BDDMockito.when(logRepository.findById(11L)).thenReturn(Optional.of(logModel));
		ResponseEntity<String> response = restTemplate.getForEntity("/logs/find-by-id/test", String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(400);
	}
	
	@Test
	public void findByIdHttpStatus405() {
		BDDMockito.when(logRepository.findAll()).thenReturn(logs);
		ResponseEntity<String> response = restTemplate.postForEntity("/logs/find-by-id/11", logModel, String.class );
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(405);
	}
	
	/**
	 * FindByAll
	 */
	@Test
	public void findAllHttpStatus200() {
		BDDMockito.when(logRepository.findAll()).thenReturn(logs);
		ResponseEntity<List> response = restTemplate.getForEntity("/logs/find-all", List.class);
		Assertions.assertThat(response.getBody().size()).isEqualTo(10);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void findAllHttpStatus401() {
		restTemplate = restTemplate.withBasicAuth("test", "test");
		BDDMockito.when(logRepository.findAll()).thenReturn(logs);
		ResponseEntity<String> response = restTemplate.getForEntity("/logs/find-all", String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(401);
	}
	
	@Test
	public void findAllHttpStatus404() {
		BDDMockito.when(logRepository.findAll()).thenReturn(logs);
		ResponseEntity<String> response = restTemplate.getForEntity("/log/find-all", String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}
	
	@Test
	public void findAllHttpStatus405() {
		BDDMockito.when(logRepository.findAll()).thenReturn(logs);
		ResponseEntity<String> response = restTemplate.postForEntity("/logs/find-all", logs, String.class );
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(405);
	}
	
	/**
	 * FindByParams
	 */
	@Test
	public void findByParamsHttpStatus400() {
		String param = "192.168.169.19";
		HttpEntity<String> requestBodyString = new HttpEntity<String>(param);
		BDDMockito.when(logRepository.findLogModelsByIpIsContainingOrRequestIsContainingOrUserAgentIsContaining(null, null, null)).thenReturn(logs);
		ResponseEntity<String> exchange = restTemplate.exchange("/logs/find-by-params", HttpMethod.GET, null, String.class);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(400);
	}
	
	@Test
	public void findByParamsHttpStatus401() {
		restTemplate = restTemplate.withBasicAuth("test", "test");
		String param = "192.168.169.19";
		HttpEntity<String> requestBodyString = new HttpEntity<String>(param);
		BDDMockito.when(logRepository.findLogModelsByIpIsContainingOrRequestIsContainingOrUserAgentIsContaining(param, param, param)).thenReturn(logs);
		ResponseEntity<String> exchange = restTemplate.exchange("/logs/find-by-params", HttpMethod.GET, requestBodyString, String.class);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(401);
	}
	
	@Test
	public void findByParamsHttpStatus404() {
		String param = "192.168.169.19";
		HttpEntity<String> requestBodyString = new HttpEntity<String>(param);
		BDDMockito.when(logRepository.findLogModelsByIpIsContainingOrRequestIsContainingOrUserAgentIsContaining(param, param, param)).thenReturn(null);
		ResponseEntity<String> exchange = restTemplate.exchange("/logs/find-by-params/1", HttpMethod.GET, requestBodyString, String.class);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(404);
	}
	
	@Test
	public void findByParamsHttpStatus405() {
		String param = "192.168.169.19";
		HttpEntity<String> requestBodyString = new HttpEntity<String>(param);
		BDDMockito.when(logRepository.findLogModelsByIpIsContainingOrRequestIsContainingOrUserAgentIsContaining(param, param, param)).thenReturn(logs);
		ResponseEntity<String> exchange = restTemplate.exchange("/logs/find-by-params", HttpMethod.POST, requestBodyString, String.class);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(405);
	}
	
	/**
	 * FindByBetween
	 */
	@Test
	public void findByCreatedAtBetweenHttpStatus200() {
		BDDMockito.when(logRepository.findLogModelsByCreatedAtBetween(from, to)).thenReturn(logs);
		ResponseEntity<List> response = restTemplate.getForEntity("/logs/find-by-createdat-between/2020-01-01 00:00:23.003/2020-01-05 00:00:23.003", List.class);
		Assertions.assertThat(response.getBody().size()).isEqualTo(10);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void findByCreatedAtBetweenHttpStatus401() {
		restTemplate = restTemplate.withBasicAuth("test", "test");
		BDDMockito.when(logRepository.findLogModelsByCreatedAtBetween(from, to)).thenReturn(logs);
		ResponseEntity<String> response = restTemplate.getForEntity("/logs/find-by-createdat-between/2020-01-01 00:00:23.003/2020-01-05 00:00:23.003", String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(401);
	}
	
	@Test
	public void findByCreatedAtBetweenHttpStatus404() {
		BDDMockito.when(logRepository.findLogModelsByCreatedAtBetween(from, to)).thenReturn(logs);
		ResponseEntity<String> response = restTemplate.getForEntity("/logs/find-by-createat-between/2020-01-01 00:00:23.003/2020-01-05 00:00:23.003", String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}
	
	@Test
	public void findByCreatedAtBetweenHttpStatus405() {
		BDDMockito.when(logRepository.findLogModelsByCreatedAtBetween(from, to)).thenReturn(logs);
		ResponseEntity<String> response = restTemplate.postForEntity("/logs/find-by-createdat-between/2020-01-01 00:00:23.003/2020-01-05 00:00:23.003", logs, String.class );
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(405);
	}
	
	/**
	 * Create
	 */
	@Test
	public void createHttpStatus201() {
		restTemplate = restTemplate.withBasicAuth("admin", "smartLog2020");
		BDDMockito.when(logRepository.save(logModel)).thenReturn(logModel);
		ResponseEntity<LogModel> response = restTemplate.postForEntity("/logs/create", request, LogModel.class);
		Assertions.assertThat(response.getBody().getUserAgent()).isEqualTo(logModel.getUserAgent());
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(201);
	}
	
	@Test
	public void createHttpStatus403() {
		BDDMockito.when(logRepository.save(logModel)).thenReturn(logModel);
		ResponseEntity<String> response = restTemplate.postForEntity("/logs/create", request, String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(403);
	}
	
	@Test
	public void createHttpStatus404() {
		restTemplate = restTemplate.withBasicAuth("admin", "smartLog2020");
		BDDMockito.when(logRepository.save(logModel)).thenReturn(logModel);
		ResponseEntity<String> response = restTemplate.postForEntity("/log/create", request, String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(404);
	}
	
	@Test
	public void createHttpStatus405() {
		restTemplate = restTemplate.withBasicAuth("admin", "smartLog2020");
		BDDMockito.when(logRepository.save(logModel)).thenReturn(logModel);
		ResponseEntity<String> response = restTemplate.getForEntity("/logs/create", String.class );
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(405);
	}
	
	@Test
	public void createHttpStatus415() {
		BDDMockito.when(logRepository.save(logModel)).thenReturn(logModel);
		ResponseEntity<String> response = restTemplate.postForEntity("/logs/create", null, String.class);
		Assertions.assertThat(response.getStatusCodeValue()).isEqualTo(415);
	}
	
	/**
	 * Update
	 */
	@Test
	public void updateHttpStatus200() {
		restTemplate = restTemplate.withBasicAuth("admin", "smartLog2020");
		BDDMockito.when(logRepository.save(logModel)).thenReturn(logModel);
		BDDMockito.when(logRepository.existsById(11L)).thenReturn(true);
		ResponseEntity<LogModel> exchange = restTemplate.exchange("/logs/update", HttpMethod.PUT, request, LogModel.class);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void updateHttpStatus400() {
		BDDMockito.when(logRepository.save(logModel)).thenReturn(logModel);
		BDDMockito.when(logRepository.existsById(11L)).thenReturn(true);
		ResponseEntity<LogModel> exchange = restTemplate.exchange("/logs/update", HttpMethod.PUT, null, LogModel.class);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(400);
	}
	
	@Test
	public void updateHttpStatus403() {
		BDDMockito.when(logRepository.save(logModel)).thenReturn(logModel);
		BDDMockito.when(logRepository.existsById(11L)).thenReturn(true);
		ResponseEntity<LogModel> exchange = restTemplate.exchange("/logs/update", HttpMethod.PUT, request, LogModel.class);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(403);
	}
	
	@Test
	public void updateHttpStatus404() {
		restTemplate = restTemplate.withBasicAuth("admin", "smartLog2020");
		BDDMockito.when(logRepository.save(logModel)).thenReturn(logModel);
		BDDMockito.when(logRepository.existsById(11L)).thenReturn(false);
		ResponseEntity<LogModel> exchange = restTemplate.exchange("/log/update", HttpMethod.PUT, request, LogModel.class);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(404);
	}
	
	@Test
	public void updateHttpStatus405() {
		restTemplate = restTemplate.withBasicAuth("admin", "smartLog2020");
		BDDMockito.when(logRepository.save(logModel)).thenReturn(logModel);
		BDDMockito.when(logRepository.existsById(11L)).thenReturn(true);
		ResponseEntity<LogModel> exchange = restTemplate.exchange("/logs/update", HttpMethod.POST, request, LogModel.class);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(405);
	}
	
	/**
	 * Delete
	 */
	@Test
	public void deleteHttpStatus200() {
		restTemplate = restTemplate.withBasicAuth("admin", "smartLog2020");
		BDDMockito.doNothing().when(logRepository).deleteById(11L);
		BDDMockito.when(logRepository.existsById(11L)).thenReturn(true);
		ResponseEntity<String> exchange = restTemplate.exchange("/logs/delete/11", HttpMethod.DELETE, null, String.class);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(200);
	}
	
	@Test
	public void deleteHttpStatus401() {
		restTemplate = restTemplate.withBasicAuth("test", "smartLog2020");
		BDDMockito.doNothing().when(logRepository).deleteById(11L);
		BDDMockito.when(logRepository.existsById(11L)).thenReturn(true);
		ResponseEntity<String> exchange = restTemplate.exchange("/logs/delete/11", HttpMethod.DELETE, null, String.class);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(401);
	}
	
	@Test
	public void deleteHttpStatus403() {
		BDDMockito.doNothing().when(logRepository).deleteById(11L);
		BDDMockito.when(logRepository.existsById(11L)).thenReturn(true);
		ResponseEntity<String> exchange = restTemplate.exchange("/logs/delete/11", HttpMethod.DELETE, null, String.class);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(403);
	}
	
	@Test
	public void deleteHttpStatus404() {
		restTemplate = restTemplate.withBasicAuth("admin", "smartLog2020");
		BDDMockito.doNothing().when(logRepository).deleteById(11L);
		BDDMockito.when(logRepository.existsById(11L)).thenReturn(false);
		ResponseEntity<String> exchange = restTemplate.exchange("/log/delete/11", HttpMethod.DELETE, null, String.class);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(404);
	}
	
	@Test
	public void deleteHttpStatus405() {
		restTemplate = restTemplate.withBasicAuth("admin", "smartLog2020");
		BDDMockito.doNothing().when(logRepository).deleteById(11L);
		BDDMockito.when(logRepository.existsById(11L)).thenReturn(true);
		ResponseEntity<String> exchange = restTemplate.exchange("/logs/delete/11", HttpMethod.GET, null, String.class);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(405);
	}
	
	@Test
	public void deleteHttpStatus500() {
		restTemplate = restTemplate.withBasicAuth("admin", "smartLog2020");
		BDDMockito.doNothing().when(logRepository).deleteById(11L);
		BDDMockito.when(logRepository.existsById(11L)).thenReturn(true);
		ResponseEntity<String> exchange = restTemplate.exchange("/logs/delete/test", HttpMethod.DELETE, null, String.class);
		Assertions.assertThat(exchange.getStatusCodeValue()).isEqualTo(500);
	}
	
}
	
	
