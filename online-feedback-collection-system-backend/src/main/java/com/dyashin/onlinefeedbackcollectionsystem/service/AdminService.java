package com.dyashin.onlinefeedbackcollectionsystem.service;

import java.util.List;

import com.dyashin.onlinefeedbackcollectionsystem.dto.AdminDTO;
import com.dyashin.onlinefeedbackcollectionsystem.entity.Feedback;
import com.dyashin.onlinefeedbackcollectionsystem.entity.Product;

public interface AdminService {

	boolean login(AdminDTO adminDTO);

	List<Feedback> viewAllFeedback();

	List<Feedback> viewByFeedbackReviewed(boolean feedbackReviewed);

	List<Feedback> viewFeedbackByProduct(int productId);

	List<Feedback> viewFeedbackByRating(int feedbackRating);

	boolean markFeedbackAsReviewed(int feedbackId);

}
