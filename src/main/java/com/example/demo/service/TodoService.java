package com.example.demo.service;

import java.util.List;
import java.util.concurrent.CompletableFuture;
import java.util.concurrent.ExecutionException;
import java.util.stream.Stream;

import org.slf4j.MDC;
import org.springframework.stereotype.Service;

import com.example.demo.client.TodoClient;
import com.example.demo.dto.Todo;

import lombok.RequiredArgsConstructor;
import lombok.extern.log4j.Log4j2;

@Service
@Log4j2
@RequiredArgsConstructor
public class TodoService {
	private final TodoClient todoClient;
	public List<Todo> getTodoList() {
		long startMillis = System.currentTimeMillis();
		var lista = todoClient.getTodoList();
		long finalMillis = System.currentTimeMillis();
		log.info("Total: {}ms ", (finalMillis - startMillis));
		return lista;
	}
	
	public List<Todo> getTodoListAllSync() {
		long startMillis = System.currentTimeMillis();
		var lista1 = todoClient.getTodoList();
		var lista2 = todoClient.getTodoList();
		var lista3 = todoClient.getTodoList();
		var lista4 =todoClient.getTodoList();
		var listas = List.of(lista1, lista2, lista3, lista4);
		long finalMillis = System.currentTimeMillis();
		log.info("Total: {}ms ", (finalMillis - startMillis));
		return listas.stream().flatMap(List::stream).toList();
	}
	
	public List<Todo> getTodoListAllAsync() {
		long startMillis = System.currentTimeMillis();
		var traceId = MDC.get("traceId");
		System.out.println(traceId);
		CompletableFuture<List<Todo>> future1  
		  = CompletableFuture.supplyAsync(() -> {
			  MDC.put("traceId", traceId);
			 return  todoClient.getTodoList();
		  });
		CompletableFuture<Todo> future2  
		  = CompletableFuture.supplyAsync(() -> {
			 var contextMap = MDC.getCopyOfContextMap();
			 System.out.println(contextMap);
			  return todoClient.getTodo();
		  });
		CompletableFuture<List<Todo>> future3  
		  = CompletableFuture.supplyAsync(() -> todoClient.getTodoList());
		CompletableFuture<List<Todo>> future4  
		  = CompletableFuture.supplyAsync(() -> todoClient.getTodoList());
		CompletableFuture<String> futures = CompletableFuture.allOf(future1, future2, future3, future4)
		.thenApplyAsync(res -> {
			List<Todo> uno;
			try {
				uno = future1.get();
				var dos = future2.get();
				System.out.println(uno);
				System.out.println(dos);
			} catch (InterruptedException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			} catch (ExecutionException e) {
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			
			return "Hello";
		});
//		var listas = Stream.of(future1, future2, future3, future4)
//				  .map(CompletableFuture::join)
//				  .flatMap(futures -> {
//					  futures.
//				  })
//				  .toList();

		futures.join();
		long finalMillis = System.currentTimeMillis();
		log.info("Total: {}ms ", (finalMillis - startMillis));
		return List.of();
	}
}
