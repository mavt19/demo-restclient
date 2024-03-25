package com.example.demo.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.example.demo.dto.Todo;
import com.example.demo.service.TodoService;
import com.example.demo.util.Utils;

import lombok.RequiredArgsConstructor;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;


@RestController
@RequestMapping("/api/v1/todo")
@RequiredArgsConstructor
public class TodoController {

	 private final Logger logger = LoggerFactory.getLogger(TodoController.class);
	 private final TodoService todoService;
	@GetMapping("/name")
	public String getMethodName() {
		logger.info("Hola");
		return "Hello, World";
	}
	
	@GetMapping
	public List<Todo> getTodoList() {
		logger.info("Request from method  getTodoList : {}");
		List<Todo> todoList = todoService.getTodoList();
		logger.info("Response from method getTodoList : {}", Utils.ObjectToJsonString(todoList));
		return todoList;
		
	}
	
	@GetMapping("/allSync")
	public List<Todo> getTodoListAllSync() {
		logger.info("Request from method  getTodoList : {}");
		List<Todo> todoList = todoService.getTodoListAllSync();
		logger.info("Response from method getTodoList : {}", Utils.ObjectToJsonString(todoList));
		return todoList;
		
	}
	
	@GetMapping("/allAsync")
	public List<Todo> getTodoListAllAsync() {
		logger.info("Request from method  getTodoList : {}");
		List<Todo> todoList = todoService.getTodoListAllAsync();
		logger.info("Response from method getTodoList : {}", Utils.ObjectToJsonString(todoList));
		return todoList;
		
	}
	
}
