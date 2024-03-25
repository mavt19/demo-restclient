package com.example.demo.service;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.when;

import java.util.List;

import org.junit.jupiter.api.DisplayName;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;

import com.example.demo.client.TodoClient;
import com.example.demo.dto.Todo;

@ExtendWith(MockitoExtension.class)
class TodoServiceTest {

	@Mock
	private TodoClient todoClient;
	// When using Mockito Use @InjectMocks to inject
	// Mocked beans to following class
	@InjectMocks
	private TodoService todoService;

	@Test
	@DisplayName("obtener lista desde un rest client")
	void getAllPerson() {
		// given
		Todo todo = new Todo(1, 1, "Ahnis", true);
		Todo todo2 = new Todo(2, 2, "Saksham", false);
		// When
		when(todoClient.getTodoList()).thenReturn(List.of(todo, todo2));
		var personList = todoService.getTodoList();
		// Then
		// Make sure to import assertThat From org.assertj.core.api package
		assertThat(personList).isNotNull();
		assertThat(personList.size()).isEqualTo(2);
	}

}
