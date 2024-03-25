package com.example.demo.filter;

import java.io.IOException;
import java.text.SimpleDateFormat;
import java.time.LocalDate;
import java.util.Date;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.context.annotation.Configuration;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.config.annotation.InterceptorRegistry;
import org.springframework.web.servlet.config.annotation.WebMvcConfigurer;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Configuration
public class RequestTimingFilter implements WebMvcConfigurer {
	
	@Override
	public void addInterceptors(InterceptorRegistry registry) {
		// TODO Auto-generated method stub
		registry.addInterceptor(new RequestInterceptor());
	}

//	@Override
//	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
//			throws IOException, ServletException {
//		long startMillis = System.currentTimeMillis();
//		
//		try {
//			doBeforeRequest(request);
//		} finally {
//			doAfterRequest(request, response, startMillis);
//		}
//		
//	}
//
//	private void doBeforeRequest(ServletRequest request) {
//		log.info("Start Service : {} -> {}", getURI(request), getFormattedStartTime());
//	}
//
//
//
//	private String getURI(ServletRequest request) {
//		HttpServletRequest req = (HttpServletRequest) request;
//		return req.getRequestURI();
//	}
//	
//	private String getFormattedStartTime() {
//		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
//	}
//
//	private void doAfterRequest(ServletRequest request, ServletResponse response, long startMillis) {
//		long totalTime = System.currentTimeMillis() - startMillis;
//		log.info("End Service : {} : Total: {}ms : Status Code: {}", getURI(request), totalTime, getStatusCode(response));
//		
//	}
//
//	private Integer getStatusCode(ServletResponse response) {
//		HttpServletResponse res = (HttpServletResponse) response;
//		return res.getStatus();
//	}
	
}
