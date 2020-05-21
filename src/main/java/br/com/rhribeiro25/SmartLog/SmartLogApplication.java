package br.com.rhribeiro25.SmartLog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;
import org.springframework.transaction.annotation.EnableTransactionManagement;

@SpringBootApplication
@EnableJpaRepositories
public class SmartLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartLogApplication.class, args);
	}

}
