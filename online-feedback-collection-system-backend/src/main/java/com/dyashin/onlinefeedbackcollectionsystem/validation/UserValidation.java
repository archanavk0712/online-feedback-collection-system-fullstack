package com.dyashin.onlinefeedbackcollectionsystem.validation;

import com.dyashin.onlinefeedbackcollectionsystem.dto.UserDTO;
import com.dyashin.onlinefeedbackcollectionsystem.exception.FeedbackException;

public class UserValidation {
	public static void validateUserDTO(UserDTO userDTO) {
		if (userDTO == null) {
			throw new FeedbackException("User data cannot be null");
		}

		if (userDTO.getUserName() == null || userDTO.getUserName().trim().isEmpty()) {
			throw new FeedbackException("User name cannot be empty");
		}

		if (userDTO.getUserEmail() == null || !userDTO.getUserEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			throw new FeedbackException("Invalid email format");
		}

		if (userDTO.getUserPassword() == null || userDTO.getUserPassword().length() < 6) {
			throw new FeedbackException("Password must be at least 6 characters long");
		}
	}

	public static void validateLogin(String userEmail, String userPassword) {
		if (userEmail == null || !userEmail.matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			throw new FeedbackException("Invalid email");
		}

		if (userPassword == null || userPassword.trim().isEmpty()) {
			throw new FeedbackException("Password cannot be empty");
		}
	}
}
