package com.dyashin.onlinefeedbackcollectionsystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dyashin.onlinefeedbackcollectionsystem.dto.FeedbackDTO;
import com.dyashin.onlinefeedbackcollectionsystem.entity.Feedback;
import com.dyashin.onlinefeedbackcollectionsystem.service.FeedbackService;
import com.dyashin.onlinefeedbackcollectionsystem.validation.FeedbackValidation;

@RestController
@RequestMapping("/feedback")
@CrossOrigin(origins = "*")
public class FeedbackController {

	@Autowired
	private FeedbackService feedbackService;

	@PostMapping("/submit")
	public ResponseEntity<?> submitFeedback(@RequestBody FeedbackDTO feedbackDTO) {
		FeedbackValidation.submitFeedback(feedbackDTO);
		boolean saved = feedbackService.submitFeedback(feedbackDTO);
		Map<String, Object> response = new HashMap<>();
		int status = saved ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value();
		response.put("status", status);
		if (saved) {
			response.put("error", false);
			response.put("message", "Feedback submitted successfully");
		} else {
			response.put("error", true);
			response.put("message", "Unable to submit feedback");
		}
		return ResponseEntity.status(status).body(response);
	}

	@GetMapping("/byuser")
	public ResponseEntity<?> viewFeedbackByUserId(@RequestParam int userId) {
		FeedbackValidation.validateUserId(userId);
		List<Feedback> list = feedbackService.viewFeedbackByUserId(userId);
		Map<String, Object> response = new HashMap<>();
		response.put("error", false);
		response.put("record", list);
		return ResponseEntity.ok(response);
	}
}
