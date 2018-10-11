package com.ecommerce2.gustavoShop.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce2.gustavoShop.model.Product;

@Repository("productRepository")
public interface ProductRepository extends JpaRepository<Product, Long> {

	Product findById(int productId);
	
	Product findByName(String productName);
	
}
