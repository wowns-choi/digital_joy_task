package com.scanner.digitaljoy.scan.model.mapper;

import java.util.List;

import org.apache.ibatis.annotations.Mapper;

import com.scanner.digitaljoy.scan.model.dto.FileDTO;

@Mapper
public interface FileMapper {
		
	int upload(FileDTO file);

	List<FileDTO> getFiles();

	FileDTO getFile(int fileNoInt);

}
