package com.ecommerce2.gustavoShop.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.security.core.Authentication;
import org.springframework.security.core.context.SecurityContextHolder;
import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.servlet.ModelAndView;

import com.ecommerce2.gustavoShop.model.User;
import com.ecommerce2.gustavoShop.service.UserService;

@Controller
public class LoginController {

	@Autowired
	UserService userService;
	
	//login
	@GetMapping({"/", "/login"})
	public ModelAndView login() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.setViewName("login");
		
		return modelAndView;
	}
	
	//registration
	@GetMapping("/register")
	public ModelAndView registration() {
		ModelAndView modelAndView = new ModelAndView();
		modelAndView.addObject("user", new User());
		modelAndView.setViewName("registration");
		
		return modelAndView;
	}
	
	@PostMapping("/register")
	public ModelAndView createNewUser(@Valid User user, BindingResult bindingResult) {
		ModelAndView modelAndView = new ModelAndView();
		if(userService.findUserByEmail(user.getEmail()) != null) {
            bindingResult
            .rejectValue("email", "error.user",
                    "There is already a user registered with the email provided");
            modelAndView.setViewName("registration");
		} else {
			userService.saveUser(user);
			
            modelAndView.addObject("successMessage", "User has been registered successfully");
            modelAndView.addObject("user", new User());
            modelAndView.setViewName("registration");
			
		}
		return modelAndView;
	}
}
