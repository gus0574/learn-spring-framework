package com.in28minutes.learnspringsecurity.resources;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TodoResource {
	
	Logger logger = LoggerFactory.getLogger(getClass());
	
	private List<Todo> todosList = List.of(new Todo("Lee", "Learn Hard"), new Todo("Lee", "Keep Going!"));

	@GetMapping(path="/todos")
	public List<Todo> retrieveAlTodos() {
		return todosList;
	}
	
	@GetMapping(path="/users/{username}/todos")
	public Todo retrieveTodosForSpecificUser(@PathVariable String username) {
		return todosList.get(0);
	}
		
	@PostMapping(path="/users/{username}/todos")
	public void createTodosForSpecificUser(@PathVariable String username, @RequestBody Todo todo) {
		logger.info("Create {} for {}", todo, username);
	}
	
}

record Todo (String username, String description) {
}