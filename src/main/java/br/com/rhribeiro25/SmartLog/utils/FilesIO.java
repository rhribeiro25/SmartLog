package br.com.rhribeiro25.SmartLog.utils;

import java.io.File;
import java.io.FileOutputStream;
import java.io.IOException;
import java.nio.file.Files;
import java.nio.file.Path;
import java.nio.file.Paths;

import org.springframework.web.multipart.MultipartFile;

public class FilesIO {
	
	public static void salvar(String path, String fileName, MultipartFile file) {
		Path dirPath = Paths.get(path);
		File newFile = new File(path + fileName);
		
		try {
			Files.createDirectories(dirPath);
			FileOutputStream fos = new FileOutputStream(newFile); 
		    fos.write(file.getBytes());
		    fos.close(); 		
		} catch (IOException e) {
			throw new RuntimeException(e.getMessage(), e);
		}		
	}
}
