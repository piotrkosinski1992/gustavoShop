package com.ecommerce2.gustavoShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce2.gustavoShop.model.Product;
import com.ecommerce2.gustavoShop.repository.ProductRepository;

@Service("productService")
public class ProductService {

	private ProductRepository productRepository;
	
	@Autowired
	public ProductService(ProductRepository productRepository) {
		this.productRepository = productRepository;
	}
	
	public Product findByProductId(int productId) {
		return productRepository.findById(productId);
	}
	
	public Product findByName(String productName) {
		return productRepository.findByName(productName);
	}
	
	public Product saveProductToDatabase(Product product) {
		productRepository.save(product);
		return product;
	}
	
	public List<Product> showAll() {
		return productRepository.findAll();
	}
	
}
