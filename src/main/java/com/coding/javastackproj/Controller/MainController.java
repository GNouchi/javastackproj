package com.coding.javastackproj.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coding.javastackproj.Models.Post;
import com.coding.javastackproj.Models.User;
import com.coding.javastackproj.Services.CategoryService;
import com.coding.javastackproj.Services.PostService;
import com.coding.javastackproj.Services.ThreadService;
import com.coding.javastackproj.Services.UserService;
import com.coding.javastackproj.Validators.UserValidator;

@Controller
public class MainController {
	private final UserService userService;
	private final UserValidator userValidator;
	private final ThreadService threadService;
	private final PostService postService;
	private final CategoryService categoryService;
	public List<String> baseCategories = new ArrayList<String>();
	
	
	public MainController(UserService userService, UserValidator userValidator, ThreadService threadService,
			PostService postService, CategoryService categoryService) {
		this.userService = userService;
		this.userValidator = userValidator;
		this.threadService = threadService;
		this.postService = postService;
		this.categoryService = categoryService;
		baseCategories.add("delicious food");
		baseCategories.add("cooking");
		baseCategories.add("dumb stuff");
		baseCategories.add("!cats");
	}

	
// MAIN PAGE
	
	@RequestMapping( {"/", "/index", "/home","/login", "/register"} )
	public String index( 
			@ModelAttribute("user") User user
			, HttpSession session
			, Model model
	) {
		if(session.getAttribute("userid")!=null) {			
			Long userid =  (Long) session.getAttribute("userid");			
			model.addAttribute("user", userService.findById(userid));
			System.out.println(baseCategories);
			model.addAttribute("categoryOptions" , baseCategories);
		}		
		return "index";
	}
	
//	Show Thread
	@RequestMapping("/show/{id}")
	public String showThread(
		@PathVariable("id")Long id
		, Model model
		, @ModelAttribute("post") Post post) {
		threadService
		return "show";	
	}
	
	
	
//~~~~~~~~~~~	Operations ~~~~~~~~~~~~//
// 	registration	
	
	@RequestMapping(value="/register" , method = RequestMethod.POST)
	public String register(@Valid @ModelAttribute("user") User user, BindingResult result,  HttpSession session ) {
		userValidator.validate(user, result);
		if(result.hasErrors()) {
			return "index";
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

// create thread
//	@RequestMapping(value = "/newthread", method=RequestMapping.POST )
//	public String createThread(
//		@Valid@ModelAttribute("thread") Thread thread
//		, BindingResult result
//		, HttpSession session			
//	) {
//		User current_user = userService.findById((Long) session.getAttribute("userid"));
//		Thread newthread = threadService.createThread(current_user, thread);
//		return"index";
//	}
	

	
}
