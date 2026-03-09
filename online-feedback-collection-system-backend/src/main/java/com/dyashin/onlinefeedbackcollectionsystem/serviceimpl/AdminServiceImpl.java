package com.dyashin.onlinefeedbackcollectionsystem.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dyashin.onlinefeedbackcollectionsystem.dao.AdminDAO;
import com.dyashin.onlinefeedbackcollectionsystem.dao.FeedbackDAO;
import com.dyashin.onlinefeedbackcollectionsystem.dto.AdminDTO;
import com.dyashin.onlinefeedbackcollectionsystem.entity.Admin;
import com.dyashin.onlinefeedbackcollectionsystem.entity.Feedback;
import com.dyashin.onlinefeedbackcollectionsystem.service.AdminService;
import com.dyashin.onlinefeedbackcollectionsystem.service.EmailService;

@Service
public class AdminServiceImpl implements AdminService {

	@Autowired
	private AdminDAO adminDAO;

	@Autowired
	private FeedbackDAO feedbackDAO;
	
	@Autowired
	private EmailService emailService;

	@Override
	public boolean login(AdminDTO adminDTO) {
		Admin admin = adminDAO.findByAdminEmailAndAdminPassword(adminDTO.getAdminEmail(), adminDTO.getAdminPassword());
		return admin != null;
	}

	@Override
	public List<Feedback> viewAllFeedback() {
		return feedbackDAO.findAll();
	}

	@Override
	public List<Feedback> viewByFeedbackReviewed(boolean feedbackReviewed) {
		return feedbackDAO.findByFeedbackReviewed(feedbackReviewed);
	}

	@Override
	public List<Feedback> viewFeedbackByProduct(int productId) {
		return feedbackDAO.findByProductProductId(productId);
	}

	@Override
	public List<Feedback> viewFeedbackByRating(int feedbackRating) {
		return feedbackDAO.findByFeedbackRating(feedbackRating);
	}

	@Override
	public boolean markFeedbackAsReviewed(int feedbackId) {
		Feedback feedback = feedbackDAO.findById(feedbackId)
				.orElseThrow(() -> new RuntimeException("Feedback not found"));
		feedback.setFeedbackReviewed(true);
		feedbackDAO.save(feedback);
		String userEmail = feedback.getUser().getUserEmail();
		String userName = feedback.getUser().getUserName();
		String productName = feedback.getProduct().getProductName();
		emailService.sendFeedbackReviewedMail(userEmail, userName, productName);
		return true;
	}

}
