package com.dyashin.onlinefeedbackcollectionsystem.controller;

import java.util.HashMap;
import java.util.List;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.dyashin.onlinefeedbackcollectionsystem.dto.ProductDTO;
import com.dyashin.onlinefeedbackcollectionsystem.entity.Product;
import com.dyashin.onlinefeedbackcollectionsystem.service.ProductService;
import com.dyashin.onlinefeedbackcollectionsystem.validation.ProductValidation;

@RestController
@RequestMapping("/product")
@CrossOrigin(origins = "*")
public class ProductController {

	@Autowired
	private ProductService productService;

	@GetMapping("/viewall")
	public ResponseEntity<?> viewAllProduct() {
		List<Product> list = productService.viewAllProduct();
		Map<String, Object> response = new HashMap<>();
		response.put("error", false);
		response.put("record", list);
		return ResponseEntity.ok(response);
	}

	@PostMapping("/add")
	public ResponseEntity<?> addProduct(@RequestBody ProductDTO productDTO) {
		ProductValidation.validateProductDTO(productDTO);
		boolean added = productService.addProduct(productDTO);
		Map<String, Object> response = new HashMap<>();
		int status = added ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value();
		response.put("status", status);
		if (added) {
			response.put("error", false);
			response.put("message", "Product added successfully");
		} else {
			response.put("error", true);
			response.put("message", "Unable to add product");
		}
		return ResponseEntity.status(status).body(response);
	}

	@PutMapping("/update")
	public ResponseEntity<?> updateProduct(@RequestBody ProductDTO productDTO) {
		ProductValidation.validateProductDTO(productDTO);
		boolean updated = productService.updateProduct(productDTO);
		Map<String, Object> response = new HashMap<>();
		int status = updated ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value();
		response.put("status", status);
		if (updated) {
			response.put("error", false);
			response.put("message", "Product updated successfully");
		} else {
			response.put("error", true);
			response.put("message", "Invalid product id");
		}
		return ResponseEntity.status(status).body(response);
	}

	@DeleteMapping("/delete")
	public ResponseEntity<?> deleteProduct(@RequestParam int productId) {
		ProductValidation.validateProductId(productId);
		boolean deleted = productService.deleteProduct(productId);
		Map<String, Object> response = new HashMap<>();
		int status = deleted ? HttpStatus.OK.value() : HttpStatus.BAD_REQUEST.value();
		response.put("status", status);
		if (deleted) {
			response.put("error", false);
			response.put("message", "Product deleted successfully");
		} else {
			response.put("error", true);
			response.put("message", "Invalid product id");
		}
		return ResponseEntity.status(status).body(response);
	}
}
