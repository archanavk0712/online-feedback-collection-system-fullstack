package com.dyashin.onlinefeedbackcollectionsystem.service;

import java.util.List;

import com.dyashin.onlinefeedbackcollectionsystem.dto.ProductDTO;
import com.dyashin.onlinefeedbackcollectionsystem.entity.Product;

public interface ProductService {

	List<Product> viewAllProduct();

	boolean addProduct(ProductDTO productdto);

	boolean updateProduct(ProductDTO productdto);

	boolean deleteProduct(int productId);

}
