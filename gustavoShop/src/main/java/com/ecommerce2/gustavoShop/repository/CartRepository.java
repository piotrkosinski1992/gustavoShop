package com.ecommerce2.gustavoShop.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.ecommerce2.gustavoShop.model.Product;
import com.ecommerce2.gustavoShop.model.Role;

@Repository("roleRepository")
public interface CartRepository extends JpaRepository<Product, Long> {

	List<Product> findByUserId(String userId);
}
