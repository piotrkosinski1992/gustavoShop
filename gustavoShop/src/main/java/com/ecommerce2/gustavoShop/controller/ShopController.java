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
public class ShopController {
	
	@Autowired
	UserService userService;
	
	@Autowired
	ProductService productService;
	
	@Autowired
	CartService cartService;
	
	
    @GetMapping(value="/admin/home")
    public ModelAndView home(){
        ModelAndView modelAndView = new ModelAndView();
        Authentication auth = SecurityContextHolder.getContext().getAuthentication();
        System.out.println("%%%");
        System.out.println(auth.getAuthorities().toString());
        User user = userService.findUserByEmail(auth.getName());
        modelAndView.addObject("userName", "Welcome " + user.getName() + " " + user.getLastName() + " (" + user.getEmail() + ")");
        modelAndView.addObject("adminMessage","Content Available Only for Users with Admin Role");
        modelAndView.setViewName("admin/home");
        return modelAndView;
    }
    
    @GetMapping("/cart") 
    // rozkmina jak dostac current usera (jak store jego generowane id cartService.findProductsByUserId(userId)
    public ModelAndView showCart() {
    	ModelAndView modelAndView = new ModelAndView();
    	modelAndView.addObject("listOfOrders", cartService.showAll());
    	modelAndView.setViewName("cart");
    	
    	return modelAndView;
    }
    
    @PostMapping("/cart/add")
    public ModelAndView addProductToCartById(int productId) {
    	ModelAndView modelAndView = new ModelAndView();
    	cartService.saveProductToCart(productService.findByProductId(productId));
    	modelAndView.addObject("listOfOrders", cartService.showAll());
    	modelAndView.setViewName("cart");
    	
    	return modelAndView;
    	
    }
    
}
