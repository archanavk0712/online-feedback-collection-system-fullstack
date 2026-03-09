package com.dyashin.onlinefeedbackcollectionsystem.validation;

import com.dyashin.onlinefeedbackcollectionsystem.dto.AdminDTO;
import com.dyashin.onlinefeedbackcollectionsystem.exception.FeedbackException;

public class AdminValidation {

	public static void login(AdminDTO adminDTO) {

		if (adminDTO == null) {
			throw new FeedbackException("Admin data cannot be null");
		}

		if (!adminDTO.getAdminEmail().matches("^[A-Za-z0-9+_.-]+@(.+)$")) {
			throw new FeedbackException("Invalid admin email format");
		}

		if (adminDTO.getAdminPassword() == null || adminDTO.getAdminPassword().trim().isEmpty()) {
			throw new FeedbackException("Admin password is required");
		}

		if (adminDTO.getAdminPassword().length() < 6) {
			throw new FeedbackException("Password must be at least 6 characters long");
		}
	}

	public static void validateFeedbackId(int feedbackId) {
		if (feedbackId <= 0) {
			throw new FeedbackException("Feedback id must be positive");
		}
	}

	public static void validateProductId(int productId) {
		if (productId <= 0) {
			throw new FeedbackException("Product id must be positive");
		}
	}

	public static void validateRating(int rating) {
		if (rating < 1 || rating > 5) {
			throw new FeedbackException("Rating must be between 1 and 5");
		}
	}
}
