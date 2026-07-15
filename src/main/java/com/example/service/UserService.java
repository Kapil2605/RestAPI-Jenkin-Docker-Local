package com.example.service;

import java.util.List;
import java.util.Optional;

import com.example.entity.User;


public interface UserService {
	public User createUser(User user);
	
	public List<User> getUser();
	
	public Optional<User> getUserById(int id);
	
	public User updateUser(int id,User user);
	
	public void deleteUser(int id);
}