package com.dyashin.onlinefeedbackcollectionsystem.service;

import java.util.List;

import com.dyashin.onlinefeedbackcollectionsystem.dto.FeedbackDTO;
import com.dyashin.onlinefeedbackcollectionsystem.entity.Feedback;

public interface FeedbackService {

	boolean submitFeedback(FeedbackDTO feedbackDTO);

	List<Feedback> viewFeedbackByUserId(int userId);

}
