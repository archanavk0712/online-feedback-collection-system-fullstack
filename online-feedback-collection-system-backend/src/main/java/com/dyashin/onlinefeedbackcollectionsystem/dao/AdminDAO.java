package com.dyashin.onlinefeedbackcollectionsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dyashin.onlinefeedbackcollectionsystem.entity.Admin;

public interface AdminDAO extends JpaRepository<Admin, Integer> {
	Admin findByAdminEmailAndAdminPassword(String email, String password);
}
