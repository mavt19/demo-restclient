package com.example.demo.client;

import java.util.List;

import org.springframework.core.ParameterizedTypeReference;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestClient;

import com.example.demo.dto.Todo;
import com.example.demo.util.Utils;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Component
@Log4j2
@RequiredArgsConstructor
public class TodoClient {

	private final RestClient restClient;
//	public TodoClient(RestClient.Builder builder) {
//
//		this.restClient = builder.build();
//	}
//	public TodoClient(RestClient.Builder builder) {
//        this.restClient = builder.build();
//    }


	private static final String BASE_URL = "https://jsonplaceholder.typicode.com";
	
	
	
	public List<Todo> getTodoList() {
		log.info("Request from method  getTodoList CLIENT : {}");
		List<Todo> response = restClient
				.get()
				.uri(BASE_URL.concat("/todos"))
				.retrieve()
				.body(new ParameterizedTypeReference<>() {
				});
		log.info("Response from method getTodoList CLIENT : {}", Utils.ObjectToJsonString(response));
		return response;
		
	}
	
	
	public Todo getTodo() {
		log.info("Request from method  getTodo CLIENT : {}");
		Todo response = restClient
				.get()
				.uri(BASE_URL.concat("/todos/1"))
				.retrieve()
				.body(Todo.class);
		log.info("Response from method getTodo CLIENT : {}", Utils.ObjectToJsonString(response));
		return response;
		
	}
}
