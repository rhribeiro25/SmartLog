package br.com.rhribeiro25.SmartLog.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import br.com.rhribeiro25.SmartLog.model.LogModel;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */

public interface LogRepository extends JpaRepository<LogModel, Long> {
}
