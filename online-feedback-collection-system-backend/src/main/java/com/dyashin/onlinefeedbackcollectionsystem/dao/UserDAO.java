package com.dyashin.onlinefeedbackcollectionsystem.dao;

import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dyashin.onlinefeedbackcollectionsystem.entity.User;

public interface UserDAO extends JpaRepository<User, Integer> {
	  Optional<User> findByUserEmail(String userEmail);
}
