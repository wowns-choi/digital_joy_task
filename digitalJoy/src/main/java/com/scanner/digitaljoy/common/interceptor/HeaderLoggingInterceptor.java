package com.scanner.digitaljoy.common.interceptor;

import java.util.Enumeration;

import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Slf4j
public class HeaderLoggingInterceptor implements HandlerInterceptor{
	
	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		
		log.info("sssssssssssssssssssssssssssssssssssssss");
		
        // 모든 헤더 이름 가져오기
        Enumeration<String> headerNames = request.getHeaderNames();

        // 헤더 이름이 있는지 확인
        if (headerNames != null) {
        	log.info("HTTP 요청 헤더 :");

            // 헤더 이름들을 순회하며 출력
            while (headerNames.hasMoreElements()) {
                String headerName = headerNames.nextElement();
                String headerValue = request.getHeader(headerName);
                log.info(headerName + ": " + headerValue);
            }
        } else {
        	log.info("헤더가 존재하지 않습니다.");
        } 
        
        return true; 
		
		
	}

}
