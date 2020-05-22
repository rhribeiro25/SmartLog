package br.com.rhribeiro25.SmartLog.spring.batch;

import java.util.List;

import org.springframework.batch.item.ItemWriter;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Component;

import br.com.rhribeiro25.SmartLog.model.LogModel;
import br.com.rhribeiro25.SmartLog.service.LogService;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */

@Component
public class LogItemWriter implements ItemWriter<LogModel> {

    @Autowired
    private LogService logService;

    @Override
    public void write(List<? extends LogModel> logs) throws Exception {
        System.out.println("Data Saved for logs: " + logs);
        logService.saveAll(logs);
    }
}
