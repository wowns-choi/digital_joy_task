package com.scanner.digitaljoy.scan.model.service;

import java.io.IOException;
import java.util.List;
import java.util.Map;

import org.springframework.web.multipart.MultipartFile;

import com.scanner.digitaljoy.scan.model.dto.FileDTO;

public interface ScanService {

	Map<String, Object> upload(MultipartFile uploadFile) throws IllegalStateException, IOException;

	List<FileDTO> getFiles();

	String getFile(int fileNoInt);



}
