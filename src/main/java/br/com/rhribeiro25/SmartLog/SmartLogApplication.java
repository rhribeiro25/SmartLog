package br.com.rhribeiro25.SmartLog;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.autoconfigure.SpringBootApplication;
import org.springframework.boot.autoconfigure.domain.EntityScan;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.data.jpa.repository.config.EnableJpaRepositories;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */

@SpringBootApplication
@ComponentScan(basePackages = { "br.com.rhribeiro25.SmartLog" })
@EntityScan(basePackages = { "br.com.rhribeiro25.SmartLog.model" })
@EnableJpaRepositories(basePackages = { "br.com.rhribeiro25.SmartLog.repository" })
public class SmartLogApplication {

	public static void main(String[] args) {
		SpringApplication.run(SmartLogApplication.class, args);
	}

}
