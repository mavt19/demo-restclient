package com.example.demo.client;

import static org.assertj.core.api.Assertions.assertThat;
import static org.junit.jupiter.api.Assertions.*;
import static org.springframework.test.web.client.match.MockRestRequestMatchers.requestTo;
import static org.springframework.test.web.client.response.MockRestResponseCreators.withSuccess;

import java.util.List;

import org.junit.jupiter.api.Test;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.autoconfigure.AutoConfiguration;
import org.springframework.boot.test.autoconfigure.web.client.RestClientTest;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.context.annotation.ComponentScan;
import org.springframework.http.MediaType;
import org.springframework.test.web.client.MockRestServiceServer;

import com.example.demo.config.RestClientConfig;
import com.example.demo.dto.Todo;
import com.google.gson.Gson;

@RestClientTest(TodoClient.class)
//@SpringBootTest(classes = {
//		RestClientConfig.class
//		})
@ComponentScan(basePackageClasses = {RestClientConfig.class})
class TodoClientTest {

    @Autowired
    TodoClient client;

    @Autowired
    MockRestServiceServer server;
    
	@Test
	void shouldReturnAllTodoList() {
		
		//given
		Todo todo = new Todo(1, 1, "Ahnis", true);
		Todo todo2 = new Todo(2, 2, "Saksham", false);
		String detailsString = new Gson().toJson(List.of(todo, todo2));
		//when
		server.expect(requestTo("https://jsonplaceholder.typicode.com/todos"))
        .andRespond(withSuccess(detailsString, MediaType.APPLICATION_JSON));
		
		//then
		
		List<Todo> response = client.getTodoList();
		
		assertThat(response).isNotNull();
		assertThat(response.size()).isEqualTo(2);
	}

}
