package com.dyashin.onlinefeedbackcollectionsystem.entity;

import java.time.LocalDateTime;

import com.dyashin.onlinefeedbackcollectionsystem.dto.ProductDTO;
import com.dyashin.onlinefeedbackcollectionsystem.dto.UserDTO;
import com.fasterxml.jackson.annotation.JsonIgnore;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.FetchType;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.Table;
import lombok.Data;

@Entity
@Table(name = "feedback")
@Data
public class Feedback {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "feedback_id")
	private int feedbackId;
	
	@Column(name = "feedback_comment")
	private String feedbackComment;

	@Column(name = "feedback_rating")
	private int feedbackRating;
	
	@Column(name = "feedback_created_at")
	private LocalDateTime feedbackDate;
	
	@Column(name = "feedback_reviewed")
	private boolean feedbackReviewed;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private User user; 
	
	@ManyToOne(fetch = FetchType.EAGER)
	@JoinColumn(name = "product_id")
	private Product product;

}
