package com.dyashin.onlinefeedbackcollectionsystem.dao;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dyashin.onlinefeedbackcollectionsystem.entity.Feedback;

public interface FeedbackDAO extends JpaRepository<Feedback, Integer> {

	List<Feedback> findByProductProductId(int productId);
	
	List<Feedback> findByFeedbackRating(int feedbackRating);

	List<Feedback> findByFeedbackReviewed(boolean feedbackReviewed);
	
	List<Feedback> findByUserUserId(int userId);
	
	boolean existsByProductProductId(int productId);
	
}
