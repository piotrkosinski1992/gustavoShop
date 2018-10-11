package com.ecommerce2.gustavoShop.service;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.ecommerce2.gustavoShop.model.Product;
import com.ecommerce2.gustavoShop.repository.CartRepository;

@Service("cartService")
public class CartService {

	private CartRepository cartRepository;
	
	@Autowired
	public CartService(CartRepository cartRepository) {
		this.cartRepository = cartRepository;
	}
	
	public List<Product> findProductsByUserId(String userId) {
		return cartRepository.findByUserId(userId);
	}
	
	public Product saveProductToCart(Product product) {
		cartRepository.save(product);
		return product;
	}
	
	public List<Product> showAll() {
		return cartRepository.findAll();
	}
	
	public List<Product> findOrdersByUserId(String userId) {
		return cartRepository.findByUserId(userId);
	}
}
