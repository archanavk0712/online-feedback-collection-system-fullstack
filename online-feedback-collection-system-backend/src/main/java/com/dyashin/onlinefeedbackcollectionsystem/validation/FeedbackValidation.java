package com.dyashin.onlinefeedbackcollectionsystem.validation;

import com.dyashin.onlinefeedbackcollectionsystem.dto.FeedbackDTO;
import com.dyashin.onlinefeedbackcollectionsystem.exception.FeedbackException;

public class FeedbackValidation {

	public static void submitFeedback(FeedbackDTO feedbackDTO) {
		if (feedbackDTO == null) {
			throw new FeedbackException("Feedback data cannot be null");
		}

		if (feedbackDTO.getUserId() <= 0) {
			throw new FeedbackException("Invalid user id");
		}

		if (feedbackDTO.getProductId() <= 0) {
			throw new FeedbackException("Invalid product id");
		}

		if (feedbackDTO.getFeedbackComment() == null || feedbackDTO.getFeedbackComment().trim().isEmpty()) {
			throw new FeedbackException("Feedback comment is required");
		}

		if (feedbackDTO.getFeedbackComment().length() > 500) {
			throw new FeedbackException("Feedback comment cannot exceed 500 characters");
		}

		if (feedbackDTO.getFeedbackRating() < 1 || feedbackDTO.getFeedbackRating() > 5) {
			throw new FeedbackException("Feedback rating must be between 1 and 5");
		}
	}

	public static void validateUserId(int userId) {
		if (userId <= 0) {
			throw new FeedbackException("Invalid user id");
		}
	}
}
