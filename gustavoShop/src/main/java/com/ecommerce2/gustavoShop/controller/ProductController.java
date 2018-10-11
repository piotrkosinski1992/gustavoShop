package com.ecommerce2.gustavoShop.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce2.gustavoShop.model.User;
import com.ecommerce2.gustavoShop.service.CartService;
import com.ecommerce2.gustavoShop.service.ProductService;
import com.ecommerce2.gustavoShop.service.UserService;

@Controller
public class ProductController {
	
	@Autowired
	ProductService productService;
	
    
    @GetMapping("/products") 
    // rozkmina jak dostac current usera (jak store jego generowane id cartService.findProductsByUserId(userId)
    public ModelAndView showProducts() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("listOfProducts", productService.showAll());
    	modelAndView.setViewName("products");
    	
    	return modelAndView;
    }
    
    @PostMapping("/product/add")
    public ModelAndView addProduct(int productId) {
    	ModelAndView modelAndView = new ModelAndView();
    	cartService.saveProductToCart(productService.findByProductId(productId));
    	modelAndView.addObject("listOfOrders", cartService.showAll());
    	modelAndView.setViewName("cart");
    	
    	return modelAndView;
    	
    }
    
}
