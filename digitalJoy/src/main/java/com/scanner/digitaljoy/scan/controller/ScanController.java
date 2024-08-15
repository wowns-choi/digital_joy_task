package com.scanner.digitaljoy.scan.controller;

import java.io.IOException;
import java.time.LocalDateTime;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.http.HttpStatus;
import org.springframework.http.HttpStatusCode;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.multipart.MultipartFile;

import com.scanner.digitaljoy.scan.model.dto.FileDTO;
import com.scanner.digitaljoy.scan.model.service.ScanService;
import lombok.RequiredArgsConstructor;
import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
@RequiredArgsConstructor
public class ScanController {

	private final ScanService scanService; 
	
	@GetMapping("hello")
	public String method() {
		return "scan/fileInput";
	}

	@PostMapping("upload")
	@ResponseBody
	public ResponseEntity<Map<String, String>> uploadFile(@RequestParam("uploadFile") MultipartFile uploadFile,
							Model model) {
		
		Map<String, String> response = new HashMap<>();
		
		String uploadedFile;
		try {
			uploadedFile = scanService.upload(uploadFile);
		} catch (IOException e) {
			response.put("message", "파일 저장 중 IOException 이 발생하였습니다.");
			response.put("errorCode", "UPLOAD_FAIL_IOEXCEPTION");
			response.put("timestamp", LocalDateTime.now().toString());			
			return ResponseEntity.status(HttpStatus.INTERNAL_SERVER_ERROR).body(response);
		}
		
		if(uploadFile.equals("0")) {
			log.debug("no file uploaded");
			response.put("message", "업로드된 파일이 존재하지 않습니다.");
			response.put("errorCode", "NO_FILE_UPLOADED");
			response.put("timestamp", LocalDateTime.now().toString());
			return ResponseEntity.status(HttpStatus.BAD_REQUEST).body(response);
		}
		
		response.put("message", "파일 업로드 성공");
		response.put("errorCode", "UPLOAD_SUCCESS");
		
		return ResponseEntity.ok().body(response);
	}
	
	@GetMapping("files")
	public String getFiles (Model model) {
		
		List<FileDTO> files = scanService.getFiles();
		model.addAttribute("files", files);
		log.info("files=={}", files);
		return "scan/allRendering";
	}
	
	@GetMapping("file/{fileNo}")
	public String getFile (@PathVariable("fileNo") String fileNo,
							Model model ) {
		int fileNoInt = Integer.parseInt(fileNo);
		String uploadedFile = scanService.getFile(fileNoInt);
		model.addAttribute("uploadedFile", uploadedFile);
		return "scan/rendering";
	}
	
}
