	package com.dyashin.onlinefeedbackcollectionsystem.service;
	
	import com.dyashin.onlinefeedbackcollectionsystem.dto.UserDTO;
	import com.dyashin.onlinefeedbackcollectionsystem.entity.User;
	
	public interface UserService {
	
		boolean register(UserDTO userDTO);
	
		User login(String userEmail, String userPassword);
	
	}
