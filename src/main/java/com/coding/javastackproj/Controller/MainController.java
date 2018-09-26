package com.coding.javastackproj.Controller;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coding.javastackproj.Models.User;
import com.coding.javastackproj.Services.UserService;
import com.coding.javastackproj.Validators.UserValidator;

@Controller
public class MainController {
	private final UserService userService;
	private final UserValidator userValidator;
		
	public MainController(UserService userService, UserValidator userValidator) {
		this.userService = userService;
		this.userValidator = userValidator; 
	}
	
//		landing render

	@RequestMapping( {"/", "/index", "/home","/login", "/register"} )
	public String index( @ModelAttribute("user") User user, HttpSession session, Model model) {
		if(session.getAttribute("userid")!=null) {			
			Long userid =  (Long) session.getAttribute("userid");			
			model.addAttribute("user", userService.findById(userid));
		}		
		return "index";
	}
	
//~~~~~~~~~~~	Operations ~~~~~~~~~~~~//
// 	registration	
	
	@RequestMapping(value="/register" , method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result,  HttpSession session ) {
		userValidator.validate(user, result);
		if(result.hasErrors()) {
			return "login";
		}
			User newuser = userService.registerUser(user);
			session.setAttribute("userid", newuser.getId());
			return "redirect:/home";
	}
	
//	login
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String login(@RequestParam("username") String username,  @RequestParam("password") String password, HttpSession session, Model model) {
		if(userService.authenticateUser(username, password)) {
			session.setAttribute("userid", userService.findByUsername(username).getId());
			return "redirect:/"; 
		}
			model.addAttribute("loginerror","Invalid credentials .... (╯°□°）╯︵ ┻━┻)");
			model.addAttribute("loginretry", username);
			model.addAttribute("user", new User());
			return "index";
	}
	
//	logout
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		return "redirect:/";		
	}

	

	
}
