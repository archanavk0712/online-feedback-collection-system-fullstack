package com.dyashin.onlinefeedbackcollectionsystem.service;

public interface EmailService {

	void sendFeedbackReviewedMail(String toEmail, String userName, String productName);

}
