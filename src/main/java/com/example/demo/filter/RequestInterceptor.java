package com.example.demo.filter;

import java.text.SimpleDateFormat;
import java.util.Date;

import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;
import org.springframework.web.servlet.HandlerInterceptor;

import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;
import jakarta.servlet.http.HttpServletRequest;
import jakarta.servlet.http.HttpServletResponse;
import lombok.extern.slf4j.Slf4j;

@Component
@Order(2)
@Slf4j
public class RequestInterceptor implements HandlerInterceptor {
	private long startMillis;

	@Override
	public boolean preHandle(HttpServletRequest request, HttpServletResponse response, Object handler)
			throws Exception {
		startMillis = System.currentTimeMillis();
		doBeforeRequest(request);
		return true;
	}

	
	@Override
	public void afterCompletion(HttpServletRequest request, HttpServletResponse response, Object handler, Exception ex)
			throws Exception {
		doAfterRequest(request, response, startMillis);
	}

	private void doBeforeRequest(ServletRequest request) {
		log.info("Start Service : {} -> {}", getURI(request), getFormattedStartTime());
	}

	private String getURI(ServletRequest request) {
		HttpServletRequest req = (HttpServletRequest) request;
		return req.getRequestURI();
	}

	private String getFormattedStartTime() {
		return new SimpleDateFormat("yyyy-MM-dd HH:mm:ss.SSS").format(new Date());
	}

	private void doAfterRequest(ServletRequest request, ServletResponse response, long startMillis) {
		long finalMillis = System.currentTimeMillis();
		log.info("End Service : {} : Total: {}ms : Status Code: {}", getURI(request), (finalMillis - startMillis),
				getStatusCode(response));

	}

	private Integer getStatusCode(ServletResponse response) {
		HttpServletResponse res = (HttpServletResponse) response;
		return res.getStatus();
	}
}
