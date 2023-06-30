package com.main.user.controller;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.main.user.entity.User;
import com.main.user.service.UserService;

@RestController
@RequestMapping("/users")
public class UserController {
	
	@Autowired
	private UserService service;
	
	@PostMapping()
	public ResponseEntity<User> createUser(@RequestBody User user){
		User user1 =service.saveUser(user) ;
		return ResponseEntity.status(HttpStatus.CREATED).body(user1);
	}
	
	
	/*
	 * @PostMapping() public ResponseEntity<List<User>>createUsers(@RequestBody
	 * List<User> users){ List<User> userList =service.saveUsers(users) ; return
	 * ResponseEntity.status(HttpStatus.CREATED).body(userList); }
	 */
	
	
	
	@PostMapping("/sampleUser")
	public User createOneUser() {
		User user = new User();
		user.setUserId("1");
		user.setName("Prakash");
		user.setEmail("acb@gmail.com");
		user.setAbout("Work");
		
		return user;
		
	}
	
	@GetMapping()
	public  ResponseEntity<List<User>> getAllUser(){
		List<User> allUser = service.getAllUser();
		return ResponseEntity.ok(allUser);
	}
	
	@GetMapping("/{userId}")
	public ResponseEntity<User> getUser(@PathVariable String userId) {
		User user = service.getUser(userId);
		return ResponseEntity.ok(user);
		
	}
   
}
