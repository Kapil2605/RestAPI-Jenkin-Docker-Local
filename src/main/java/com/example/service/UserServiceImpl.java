package com.example.service;

import java.util.List;
import java.util.Optional;

import javax.management.RuntimeErrorException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.example.entity.User;
import com.example.repository.UserRepository;


@Service
public class UserServiceImpl implements UserService{

	@Autowired
	private UserRepository userRepository;
	
	@Override
	public User createUser(User user) {
		return userRepository.save(user);
	}

	@Override
	public List<User> getUser() {
		return userRepository.findAll();
	}

	@Override
	public Optional<User> getUserById(int id) {
		return userRepository.findById(id);
	}

	@Override
	public User updateUser(int id, User user) {
		User  userData=userRepository.getById(id);
		
		if(userData!=null) {
			return userRepository.save(user);
		}
		return null;
	}

	@Override
	public void deleteUser(int id) {
		userRepository.deleteById(id);
	}

}