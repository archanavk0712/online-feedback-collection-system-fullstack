package com.dyashin.onlinefeedbackcollectionsystem.serviceimpl;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.dyashin.onlinefeedbackcollectionsystem.dao.FeedbackDAO;
import com.dyashin.onlinefeedbackcollectionsystem.dao.ProductDAO;
import com.dyashin.onlinefeedbackcollectionsystem.dto.ProductDTO;
import com.dyashin.onlinefeedbackcollectionsystem.entity.Product;
import com.dyashin.onlinefeedbackcollectionsystem.exception.FeedbackException;
import com.dyashin.onlinefeedbackcollectionsystem.service.ProductService;

@Service
public class ProductServiceImpl implements ProductService {

	@Autowired
	private ProductDAO productDAO;

	@Autowired
	private FeedbackDAO feedbackDAO;

	@Override
	public List<Product> viewAllProduct() {
		return productDAO.findAll();
	}

	@Override
	public boolean addProduct(ProductDTO productdto) {
		Product product = new Product();
		product.setProductName(productdto.getProductName());
		product.setProductDescription(productdto.getProductDescription());
		if (productDAO.save(product) != null) {
			return true;
		}
		return false;
	}

	@Override
	public boolean updateProduct(ProductDTO productdto) {
		if (productDAO.existsById(productdto.getProductId())) {
			Product product = new Product();
			product.setProductId(productdto.getProductId());
			product.setProductName(productdto.getProductName());
			product.setProductDescription(productdto.getProductDescription());
			productDAO.save(product);
			return true;
		}
		return false;

	}

	@Override
	public boolean deleteProduct(int productId) {
		if (!productDAO.existsById(productId)) {
			return false;
		}

		if (feedbackDAO.existsByProductProductId(productId)) {
			throw new FeedbackException("Cannot delete product because feedback exists for this product");
		}

		productDAO.deleteById(productId);
		return true;
	}
}
