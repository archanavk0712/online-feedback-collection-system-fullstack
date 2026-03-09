package com.dyashin.onlinefeedbackcollectionsystem.dao;

import org.springframework.data.jpa.repository.JpaRepository;

import com.dyashin.onlinefeedbackcollectionsystem.entity.Product;

public interface ProductDAO  extends JpaRepository<Product, Integer>{

}
