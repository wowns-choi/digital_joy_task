package com.scanner.digitaljoy.scan.model.service;

import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.multipart.MultipartFile;

import com.scanner.digitaljoy.common.util.Utility;
import com.scanner.digitaljoy.scan.model.dto.FileDTO;
import com.scanner.digitaljoy.scan.model.mapper.FileMapper;

import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Service
@RequiredArgsConstructor
@Slf4j
@Transactional
public class ScanServiceImpl implements ScanService{
	
    @Value("${scan.file.web-path}")
    private String webPath;
    
    @Value("${scan.file.folder-path}")
    private String folderPath;
    
    private final FileMapper fileMapper;
    
    
	/**
	 * 파일 업로드 
	 */
	@Override
	public String upload(MultipartFile uploadFile) throws IllegalStateException, IOException {
		
		if(uploadFile.isEmpty()) {
			return "0";
		}
		
		String fileOriginalName= uploadFile.getOriginalFilename();
		String fileRename = Utility.fileRename(fileOriginalName);
		
		FileDTO file = FileDTO.builder()
							  .webPath(webPath)
							  .fileOriginalName(fileOriginalName)
						   	  .fileRename(fileRename)
							  .build();
		
		int result = fileMapper.upload(file);
		
		if(result == 0) {
			return "1";
		}
		
		uploadFile.transferTo(
				new File(folderPath + fileRename)
		);
		 
		return webPath + fileRename;
	}


	/**
	 * 모든 파일 조회 
	 */
	@Override
	public List<FileDTO> getFiles() {
		List<FileDTO> files = fileMapper.getFiles();
		
		for(FileDTO file : files) {
			log.info("sssssss={}", file);
		}
		
		return files;
	}


	/**
	 * 단일 파일 조회 
	 */
	@Override
	public String getFile(int fileNoInt) {
		
		FileDTO file = fileMapper.getFile(fileNoInt);
		return file.getWebPath() + file.getFileRename();
	}

	

}
