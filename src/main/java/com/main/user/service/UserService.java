package com.main.user.service;

import java.util.List;

import com.main.user.entity.User;

public interface UserService {

	
	User saveUser(User user);
	
	List<User>getAllUser();
	
	User getUser(String userId);
	
	void deleteUser(String id);
	
	User updateUser(String id);

	List<User> saveUsers(List<User> users);
}
