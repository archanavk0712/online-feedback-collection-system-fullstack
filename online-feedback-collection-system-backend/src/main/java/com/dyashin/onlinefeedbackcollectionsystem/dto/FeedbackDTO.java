package com.dyashin.onlinefeedbackcollectionsystem.dto;

import java.time.LocalDateTime;

import lombok.Data;

@Data
public class FeedbackDTO {
	
	private int feedbackId;
	
	private String feedbackComment;

	private int feedbackRating;
	
	private LocalDateTime feedbackDate;
	
	private boolean feedbackReviewed;
	
	private int userId; 
	
	private int productId;

}
