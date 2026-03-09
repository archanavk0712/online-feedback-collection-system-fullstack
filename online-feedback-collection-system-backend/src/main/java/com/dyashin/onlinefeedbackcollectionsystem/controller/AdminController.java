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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dyashin.onlinefeedbackcollectionsystem.dto.AdminDTO;
import com.dyashin.onlinefeedbackcollectionsystem.entity.Feedback;
import com.dyashin.onlinefeedbackcollectionsystem.service.AdminService;
import com.dyashin.onlinefeedbackcollectionsystem.validation.AdminValidation;

@RestController
@RequestMapping("/admin")
@CrossOrigin(origins = "*")
public class AdminController {

	@Autowired
	private AdminService adminService;

	@PostMapping("/login")
	public ResponseEntity<?> login(@RequestBody AdminDTO adminDTO) {
		AdminValidation.login(adminDTO);
		boolean loggedIn = adminService.login(adminDTO);
		Map<String, Object> response = new HashMap<>();
		int status = loggedIn ? HttpStatus.OK.value() : HttpStatus.UNAUTHORIZED.value();
		response.put("status", status);
		if (loggedIn) {
			response.put("error", false);
			response.put("message", "Logged in successfully");
		} else {
			response.put("error", true);
			response.put("message", "Unable to login");
		}
		return ResponseEntity.status(status).body(response);
	}

	@GetMapping("/viewall")
	public ResponseEntity<?> viewAllFeedback() {
		List<Feedback> list = adminService.viewAllFeedback();

		Map<String, Object> response = new HashMap<>();
		response.put("error", false);
		response.put("record", list);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/feedbackreviewed")
	public ResponseEntity<?> viewByFeedbackReviewed(@RequestParam boolean feedbackReviewed) {
		List<Feedback> list = adminService.viewByFeedbackReviewed(feedbackReviewed);

		Map<String, Object> response = new HashMap<>();
		response.put("error", false);
		response.put("record", list);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/viewbyproduct")
	public ResponseEntity<?> viewFeedbackByProduct(@RequestParam int productId) {
		AdminValidation.validateProductId(productId);
		List<Feedback> list = adminService.viewFeedbackByProduct(productId);

		Map<String, Object> response = new HashMap<>();
		response.put("error", false);
		response.put("record", list);

		return ResponseEntity.ok(response);
	}

	@GetMapping("/viewbyrating")
	public ResponseEntity<?> viewFeedbackByRating(@RequestParam int feedbackRating) {
		AdminValidation.validateRating(feedbackRating);
		List<Feedback> list = adminService.viewFeedbackByRating(feedbackRating);

		Map<String, Object> response = new HashMap<>();
		response.put("error", false);
		response.put("record", list);

		return ResponseEntity.ok(response);
	}

	@PutMapping("/markfeedbackasreviewed")
	public ResponseEntity<?> markFeedbackAsReviewed(@RequestParam int feedbackId) {
		AdminValidation.validateFeedbackId(feedbackId);
		boolean data = adminService.markFeedbackAsReviewed(feedbackId);
		Map<String, Object> response = new HashMap<>();
		int status = data ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value();
		response.put("status", status);
		if (data) {
			response.put("error", false);
			response.put("message", "Feedback marked as reviewed");
			response.put("record", data);
		} else {
			response.put("error", true);
			response.put("message", "Invalid feedback id");
		}
		return ResponseEntity.status(status).body(response);
	}

}
