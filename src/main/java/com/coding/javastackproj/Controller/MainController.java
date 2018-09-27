package com.coding.javastackproj.Controller;

import java.util.ArrayList;
import java.util.List;

import javax.servlet.http.HttpSession;
import javax.validation.Valid;

import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RequestParam;

import com.coding.javastackproj.Models.Category;
import com.coding.javastackproj.Models.Post;
import com.coding.javastackproj.Models.Thread;
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
	public List<Category> baseCategories = new ArrayList<Category>();
	public List<String> arr = new ArrayList<String>();
	
	public MainController(UserService userService, UserValidator userValidator, ThreadService threadService,
			PostService postService, CategoryService categoryService) {
		this.userService = userService;
		this.userValidator = userValidator;
		this.threadService = threadService;
		this.postService = postService;
		this.categoryService = categoryService;
		arr.add("delicious food");
		arr.add("cooking");
		arr.add("dumb stuff");
		arr.add("!cats");
		for( int i = 0; i< arr.size(); i++) {
			Category baseCat = categoryService.createCategory(arr.get(i));
			baseCategories.add(baseCat);
		}
	}

	
// MAIN PAGE	

	@RequestMapping( {"/", "/index", "/home","/login", "/register", "/createthread"} )
	public String index( 
			@ModelAttribute("user") User user
			, @ModelAttribute("thread") Thread thread
			, HttpSession session
			, Model model
	) {
		if(session.getAttribute("userid")!=null) {			
			Long userid =  (Long) session.getAttribute("userid");
			List<Category> allCats = categoryService.findAllCategories();
			model.addAttribute("current_user", userService.findById(userid));
			model.addAttribute("categoryOptions" , allCats);
//			List<Thread> allThreads = threadService.findAllThreads() ;
//			model.addAttribute("allThreads", allThreads);
		}		
		return "index";
	}
	
	
//	Show Thread
	
	@RequestMapping("/show/{threadid}")
	public String showThread(
					@PathVariable("threadid")Long threadid
					, Model model
					, @ModelAttribute("post") Post post
					, HttpSession session 
	) {
		Long userid =  (Long) session.getAttribute("userid");	
		Thread current_thread = threadService.findThreadById(threadid);
		if(session.getAttribute("userid")==null) {
			return "redirect:/";
		}
		if(current_thread!=null) {
			model.addAttribute("current_thread",current_thread);			
		}
		model.addAttribute("current_user", userService.findById(userid));
		model.addAttribute("current_thread",current_thread);
		return "show";	
	}
	
	
	
//~~~~~~~~~~~ USER Operations ~~~~~~~~~~~~//

// 	CREATE A USER	
	
	@RequestMapping(value="/register" , method = RequestMethod.POST)
	public String register(
					@Valid @ModelAttribute("user") User user
					, BindingResult result
					,  HttpSession session 
	) {
		userValidator.validate(user, result);
		if(result.hasErrors()) {
			return "index";
		}
			User newuser = userService.registerUser(user);
			session.setAttribute("userid", newuser.getId());
			return "redirect:/home";
	}
	
//	LOGIN
	
	@RequestMapping(value="login", method = RequestMethod.POST)
	public String login(
					@RequestParam("username") String username
					,  @RequestParam("password") String password
					, HttpSession session
					, Model model
	) {
		if(userService.authenticateUser(username, password)) {
			session.setAttribute("userid", userService.findByUsername(username).getId());
			return "redirect:/"; 
		}
			model.addAttribute("loginerror","Invalid credentials .... (╯°□°）╯︵ ┻━┻)");
			model.addAttribute("loginretry", username);
			model.addAttribute("user", new User());
			return "index";
	}
	
//	LOGOUT
	
	@RequestMapping("/logout")
	public String logout(HttpSession session) {
		session.invalidate();
		System.out.println("bye felicia...");
		return "redirect:/";		
	}
	
// ~~~~~~~~~ Main Operations ~~~~~~~~~ //	
	
// Create Thread
	
	@RequestMapping(value = "/createthread", method = RequestMethod.POST)
	public String createThread(
		@RequestParam("categories") String categories
		, @Valid @ModelAttribute("thread") Thread thread
		, BindingResult result
		, HttpSession session
		, Model model
	) {		
		if( result.hasErrors() ) {
			System.out.println("errors found this is not gonna work! ");
			List<ObjectError> x = result.getAllErrors();
				for(ObjectError err:x) 
				{
					System.out.println(err);
				}			
			Long userid =  (Long) session.getAttribute("userid");
			List<Category> allCats = categoryService.findAllCategories();
			model.addAttribute("current_user", userService.findById(userid));
			model.addAttribute("categoryOptions" , allCats);
			return "index";
		}
				System.out.println("sending to threadservice...");
		thread.setCreator(userService.findById((Long) session.getAttribute("userid")));
		Thread newthread = threadService.createThread(thread);		
				System.out.println("created : " +  newthread + "  redirecting to index! ");
		return"redirect:/show/"+newthread.getId();
	}
	
	
	
	
//	Create Post
	@RequestMapping(value="/show/{threadid}", method = RequestMethod.POST)
	public String createPost(
					HttpSession session
					, @PathVariable("threadid") Long threadid
					, @Valid @ModelAttribute("post") Post post
					, BindingResult result
	) {
		System.out.println("hit post create!");
		if(result.hasErrors()) {
			List<ObjectError> x = result.getAllErrors();
				System.out.println("errors found...");
				System.out.println(result.getErrorCount());
				System.out.println(x);
		return "redirect:/show/"+ threadid;
		}
				System.out.println("sending to PostService...");
		Post newpost= postService.createPost(post);
				System.out.println("result : " + newpost);
		return"redirect:/show/"+ threadid;
	}
	
}
