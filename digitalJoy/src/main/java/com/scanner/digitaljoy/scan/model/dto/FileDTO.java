package com.scanner.digitaljoy.scan.model.dto;

import java.time.LocalDateTime;

import lombok.AllArgsConstructor;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;
import lombok.ToString;

@Getter
@Setter
@ToString
@NoArgsConstructor
@AllArgsConstructor
@Builder
public class FileDTO {
	private Integer fileNo; 
	private String webPath;
	private String fileOriginalName; 
	private String fileRename; 
	private LocalDateTime fileUploadTime;
}
