package com.dyashin.onlinefeedbackcollectionsystem.controller;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dyashin.onlinefeedbackcollectionsystem.dto.UserDTO;
import com.dyashin.onlinefeedbackcollectionsystem.entity.User;
import com.dyashin.onlinefeedbackcollectionsystem.service.UserService;
import com.dyashin.onlinefeedbackcollectionsystem.validation.UserValidation;

@RestController
@RequestMapping("/user")
@CrossOrigin(origins = "*")
public class UserController {

	@Autowired
	private UserService userService;

	@PostMapping("/register")
	public ResponseEntity<?> register(@RequestBody UserDTO userDTO) {
		UserValidation.validateUserDTO(userDTO);
		boolean registered = userService.register(userDTO);
		Map<String, Object> response = new HashMap<>();
		int status = registered ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value();
		response.put("status", status);
		if (registered) {
			response.put("error", false);
			response.put("message", "User registered successfully");
		} else {
			response.put("error", true);
			response.put("message", "Email already exists");
		}
		return ResponseEntity.status(status).body(response);
	}

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody UserDTO userDTO) {
		UserValidation.validateLogin(userDTO.getUserEmail(), userDTO.getUserPassword());
		User user = userService.login(userDTO.getUserEmail(), userDTO.getUserPassword());
		Map<String, Object> response = new HashMap<>();
		int status = (user != null) ? HttpStatus.OK.value() : HttpStatus.UNAUTHORIZED.value();
		response.put("status", status);
		if (user != null) {
			response.put("error", false);
			response.put("message", "Login successful");
			response.put("record", user);
		} else {
			response.put("error", true);
			response.put("message", "Invalid email or password");
		}
		return ResponseEntity.status(status).body(response);
	}
}
