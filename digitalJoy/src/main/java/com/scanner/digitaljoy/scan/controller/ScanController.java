package com.scanner.digitaljoy.scan.controller;

import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;

import lombok.extern.slf4j.Slf4j;

@Slf4j
@Controller
public class ScanController {

	@GetMapping("hello")
	public String method() {
		
		return "scan/rendering";
		
	}
}
