package com.example.demo.filter;

import java.io.IOException;
import java.util.UUID;

import org.slf4j.MDC;
import org.springframework.core.annotation.Order;
import org.springframework.stereotype.Component;

import jakarta.servlet.Filter;
import jakarta.servlet.FilterChain;
import jakarta.servlet.ServletException;
import jakarta.servlet.ServletRequest;
import jakarta.servlet.ServletResponse;

@Component
@Order(1)
public class MdcFilter implements Filter {

	private static final String TRACE_ID = "traceId";	
	@Override
	public void doFilter(ServletRequest request, ServletResponse response, FilterChain chain)
			throws IOException, ServletException {
		try {
			var traceId = MDC.get(TRACE_ID);
			if (traceId == null) {
				traceId = UUID.randomUUID().toString();
				MDC.put(TRACE_ID, traceId);
			}
			MDC.put(TRACE_ID, traceId);
			
			chain.doFilter(request, response);
		} finally{
			MDC.remove(TRACE_ID);
			
		}
		
	}

}
