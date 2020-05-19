package br.com.rhribeiro25.SmartLog.service.impl;

import java.io.BufferedReader;
import java.io.BufferedWriter;
import java.io.File;
import java.io.FileReader;
import java.io.FileWriter;
import java.io.IOException;
import java.io.Writer;
import java.util.ArrayList;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;

import br.com.rhribeiro25.SmartLog.model.LogModel;
import br.com.rhribeiro25.SmartLog.repository.LogRepository;
import br.com.rhribeiro25.SmartLog.service.LogService;

/**
 * @author Renan Ribeiro
 * @date 18/05/2020.
 */

@Service
public class LogServiceImpl implements LogService {

	@Value("${files.path}")
	private String filesPath;

	@Autowired
	private LogRepository logRepository;

	private static File fileLogs;

	public void saveFileLogs(File file) {
		fileLogs = file;
	}

	public boolean saveNewLog(String log) throws IOException {
		Writer writer = null;
		try {
			if (fileLogs != null) {
				writer = new BufferedWriter(new FileWriter(fileLogs, true));
				writer.write("\n" + log);
			} else {
				throw new IOException("Please start the system in shell, using (best-trip fileName)!");
			}
		} finally {
			if (writer != null)
				writer.close();
		}
		return true;
	}

	public List<LogModel> findAllLogs() throws IOException {
		List<LogModel> logs = new ArrayList<>();
		BufferedReader br = null;
		try {
			if (fileLogs != null) {
				br = new BufferedReader(new FileReader(fileLogs));
				String line;
				LogModel logAux;
				while ((line = br.readLine()) != null) {
					String[] attributes = line.split(",");
					logAux = new LogModel();
					logs.add(logAux);
				}
			} else {
				throw new IOException("Please start the system in shell, using (best-trip fileName)!");
			}
		} finally {
			if (br != null)
				br.close();
		}
		return logs;
	}

	
}
