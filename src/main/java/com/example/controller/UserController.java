package com.example.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.example.entity.User;
import com.example.exception.UserNotFoundException;
import com.example.service.UserService;


@RestController
public class UserController {

	@Autowired
	private UserService userService;
	
	@PostMapping("/user")
	public User addUserDetails(@RequestBody User user) {
		return userService.createUser(user);
	}
	
	@GetMapping("/user")
	public List<User> getAllUser(){
		return userService.getUser();
	}
	
	@GetMapping("/user/{id}")
	public ResponseEntity<User> getUserDetailsById(@PathVariable int id){
		User user=userService.getUserById(id).orElse(null);
		if(user!=null) {
			return ResponseEntity.ok().body(user);
		}
		else {
			throw new UserNotFoundException("User not found");
		}
	}
	
	@PutMapping("/user/{id}")
	public ResponseEntity<User> updateUserDetails(@PathVariable int id,@RequestBody User user){
		User userData=userService.updateUser(id, user);
		if(userData!=null) {
			return ResponseEntity.ok().body(user);
		}
		else {
			return ResponseEntity.notFound().build();
		}
	}
	
	@DeleteMapping("/user/{id}")
	public ResponseEntity<Map<String, String>> deleteUser(@PathVariable int id){
		userService.deleteUser(id);
		Map<String, String> response = new HashMap<>();
	    response.put("message", "User deleted successfully");
	    
	    return ResponseEntity.ok(response);
	}
}