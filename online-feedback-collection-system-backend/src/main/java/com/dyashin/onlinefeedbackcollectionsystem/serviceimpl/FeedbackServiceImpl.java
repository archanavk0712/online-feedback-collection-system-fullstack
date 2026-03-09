package com.dyashin.onlinefeedbackcollectionsystem.serviceimpl;

import java.time.LocalDateTime;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dyashin.onlinefeedbackcollectionsystem.dao.FeedbackDAO;
import com.dyashin.onlinefeedbackcollectionsystem.dao.ProductDAO;
import com.dyashin.onlinefeedbackcollectionsystem.dao.UserDAO;
import com.dyashin.onlinefeedbackcollectionsystem.dto.FeedbackDTO;
import com.dyashin.onlinefeedbackcollectionsystem.entity.Feedback;
import com.dyashin.onlinefeedbackcollectionsystem.entity.Product;
import com.dyashin.onlinefeedbackcollectionsystem.entity.User;
import com.dyashin.onlinefeedbackcollectionsystem.service.FeedbackService;

@Service
public class FeedbackServiceImpl implements FeedbackService{
	
	@Autowired
	private FeedbackDAO feedbackDAO;

	@Autowired
	private UserDAO userDAO;
	
	@Autowired
	private ProductDAO productDAO;
	
	@Override
	public boolean submitFeedback(FeedbackDTO feedbackDTO) {
		
		User user=userDAO.findById(feedbackDTO.getUserId()).orElseThrow();
		Product product=productDAO.findById(feedbackDTO.getProductId()).orElseThrow();
		
		Feedback feedback=new Feedback();
		feedback.setFeedbackComment(feedbackDTO.getFeedbackComment());
		feedback.setFeedbackRating(feedbackDTO.getFeedbackRating());
		feedback.setFeedbackReviewed(false);
	    feedback.setFeedbackDate(LocalDateTime.now());
		feedback.setUser(user);
		feedback.setProduct(product);
		if(feedbackDAO.save(feedback)!=null) {
			return true;
		}
		return false;
	}

	@Override
	public List<Feedback> viewFeedbackByUserId(int userId) {
		return feedbackDAO.findByUserUserId(userId);
	}

	
}
