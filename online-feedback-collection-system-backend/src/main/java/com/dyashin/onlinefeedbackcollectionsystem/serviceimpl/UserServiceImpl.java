package com.dyashin.onlinefeedbackcollectionsystem.serviceimpl;

import java.util.Optional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dyashin.onlinefeedbackcollectionsystem.dao.UserDAO;
import com.dyashin.onlinefeedbackcollectionsystem.dto.UserDTO;
import com.dyashin.onlinefeedbackcollectionsystem.entity.User;
import com.dyashin.onlinefeedbackcollectionsystem.service.UserService;

@Service
public class UserServiceImpl implements UserService {

	@Autowired
	private UserDAO userDAO;

	@Override
	public boolean register(UserDTO userDTO) {
		Optional<User> existingUser = userDAO.findByUserEmail(userDTO.getUserEmail());
		if (existingUser.isPresent()) {
			return false;
		}

		User user = new User();
		user.setUserName(userDTO.getUserName());
		user.setUserEmail(userDTO.getUserEmail());
		user.setUserPassword(userDTO.getUserPassword());
		return userDAO.save(user) != null;
	}

	@Override
	public User login(String userEmail, String userPassword) {
		Optional<User> userOptional = userDAO.findByUserEmail(userEmail);
		if (userOptional.isPresent()) {
			User user = userOptional.get();
			if (userPassword != null && userPassword.equals(user.getUserPassword())) {
				return user;
			}
		}
		return null;
	}

}
