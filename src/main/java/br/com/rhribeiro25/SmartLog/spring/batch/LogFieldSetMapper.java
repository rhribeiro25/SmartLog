package br.com.rhribeiro25.SmartLog.spring.batch;

import java.util.Date;

import org.springframework.batch.item.file.mapping.FieldSetMapper;
import org.springframework.batch.item.file.transform.FieldSet;

import br.com.rhribeiro25.SmartLog.model.LogModel;
import br.com.rhribeiro25.SmartLog.utils.Formatting;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */

public class LogFieldSetMapper implements FieldSetMapper<LogModel> {

	@Override
	public LogModel mapFieldSet(FieldSet fieldSet) {
		LogModel logItem = null;
		try {
			Date createdAt = Formatting.stringToDate_yyyy_MM_dd__HH_mm_ss(fieldSet.readString(0));
			String ip = fieldSet.readString(1);
			String request = fieldSet.readString(2);
			Integer status = fieldSet.readInt(3);
			String userAgent = fieldSet.readString(4);
			logItem = new LogModel(null, createdAt, ip, request, status, userAgent);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return logItem;
	}

}
