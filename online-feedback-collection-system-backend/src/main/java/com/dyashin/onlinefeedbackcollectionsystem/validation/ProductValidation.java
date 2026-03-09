package com.dyashin.onlinefeedbackcollectionsystem.validation;

import com.dyashin.onlinefeedbackcollectionsystem.dto.ProductDTO;
import com.dyashin.onlinefeedbackcollectionsystem.exception.FeedbackException;

public class ProductValidation {
	public static void validateProductDTO(ProductDTO productDTO) {
		if (productDTO == null) {
			throw new FeedbackException("Product details cannot be null");
		}

		if (productDTO.getProductName() == null || productDTO.getProductName().trim().isEmpty()) {
			throw new FeedbackException("Product name is required");
		}

		if (productDTO.getProductDescription() == null || productDTO.getProductDescription().trim().isEmpty()) {
			throw new FeedbackException("Product description is required");
		}
	}

	public static void validateProductId(int productId) {
		if (productId <= 0) {
			throw new FeedbackException("Invalid product id");
		}
	}
}