package com.example.demo.controller;

import static org.hamcrest.CoreMatchers.containsString;
import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.when;
import static org.springframework.test.web.servlet.request.MockMvcRequestBuilders.get;
import static org.springframework.test.web.servlet.result.MockMvcResultHandlers.print;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.content;
import static org.springframework.test.web.servlet.result.MockMvcResultMatchers.status;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.autoconfigure.web.servlet.AutoConfigureMockMvc;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.http.MediaType;
import org.springframework.test.web.servlet.MockMvc;
import org.springframework.test.web.servlet.request.MockMvcRequestBuilders;
import org.springframework.test.web.servlet.result.MockMvcResultMatchers;

import com.example.demo.dto.Todo;
import com.example.demo.service.TodoService;

@SpringBootTest
@AutoConfigureMockMvc
class TodoControllerTest {

	@Autowired
	private MockMvc mockMvc;
	
	@MockBean
	private TodoService service;

	private static final String BASE_PATH = "/api/v1/todo";

	@Test
	void shouldReturnDefaultMessage() throws Exception {
		this.mockMvc.perform(get(BASE_PATH.concat("/name"))).andDo(print()).andExpect(status().isOk())
				.andExpect(content().string(containsString("Hello, World")));
	}

	@Test
	public void getAllTodoListAPI() throws Exception {
		
		when(service.getTodoList()).thenReturn(List.of(new Todo(1, 1, "test", true)));
		mockMvc.perform(MockMvcRequestBuilders.get(BASE_PATH).accept(MediaType.APPLICATION_JSON)).andDo(print())
				.andExpect(status().isOk()).andExpect(MockMvcResultMatchers.jsonPath("$.[*]").exists())
				.andExpect(MockMvcResultMatchers.jsonPath("$.[*].userId").isNotEmpty());
	}
}
