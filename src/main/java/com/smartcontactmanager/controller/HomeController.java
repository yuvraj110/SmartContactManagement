package com.smartcontactmanager.controller;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;

import com.smartcontactmanager.dao.UserRepository;
import com.smartcontactmanager.entities.User;
import com.smartcontactmanager.helper.Message;

import jakarta.servlet.http.HttpSession;
//controller 
@Controller
public class HomeController {


	 @Autowired
	    private UserRepository userRepository;
	
	
	// home handler
	@RequestMapping("/")
	public String home(Model model)
	
	{
		model.addAttribute("title", "Home - Smart Contact Manager");
		return "home";
	}
	
	// about handler
	
	@RequestMapping("/about")
	public String about(Model model)
	
	{
		model.addAttribute("title", "About - Smart Contact Manager");
		return "about";
	}

	
	// signup handler
	
	@RequestMapping("/signup")
	public String signup(Model model)
	
	{
		model.addAttribute("title", "Register - Smart Contact Manager");
		model.addAttribute("user", new User());
		return "signup";
		
	}
	

	  //handler for registering user
		@PostMapping("/do_register")
		public String registerUser(@Valid @ModelAttribute("user") User user,  BindingResult result1,
				@RequestParam(value = "agreement", defaultValue = "false") boolean agreement, Model model,
				HttpSession session) {
			try {
				if (!agreement) {
					System.out.println("You have not accepted the terms and conditions");
					throw new Exception("You have not accepted the terms and conditions");
				}
				
				if(result1.hasErrors()) {
					System.out.println("Error "+result1.toString());
					model.addAttribute("user", user);
					return "signup";
				}
				user.setRole("ROLE_USER");
				user.setEnabled(true);
				user.setAbout(user.getAbout().trim());
				//password encode
				//user.setPassword(passwordEncoder.encode(user.getPassword()));
				

				System.out.println("Agreement " + agreement);
				System.out.println("User " + user);
				User result = this.userRepository.save(user);
				System.out.println(result);
				model.addAttribute("user", new User());
				session.setAttribute("message", new Message("Successfully Registered !!", "alert-success"));
				return "signup";
			} catch (Exception e) {
				e.printStackTrace();
				model.addAttribute("user", user);
				session.setAttribute("message", new Message("Something went wrong !!" + e.getMessage(), "alert-danger"));
				return "signup";
			}
		}
}
