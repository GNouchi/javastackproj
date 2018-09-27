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
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

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
			User current_user = userService.findById(userid);
			List<Category> allCats = categoryService.findAllCategories();
			List<Category> user_interests = current_user.getUser_interests();
				model.addAttribute("current_user", current_user);
				model.addAttribute("categoryOptions" , allCats);
				model.addAttribute("interests", current_user.getUser_interests());
//				System.out.println("interests are : "+ user_interests);
			List<String> arr = new ArrayList<String>();
				for(Category category: user_interests) {					
					System.out.println("cat : "+category.getId());
					arr.add( postService.findLastVidByCategoryId(category.getId()) );
				}
				model.addAttribute("prize", arr);
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
	
//	SHOW ALL THREADS (no videos)
	@RequestMapping("allthreads")
	public String allthreads(HttpSession session, Model model) {
		List<Thread> allthreads = threadService.findAllThreads();
		model.addAttribute("allthreads", allthreads);
		return "allthreads";
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

// add Interest(Category) to User
	@RequestMapping(value="addcategorytouser", method =RequestMethod.POST)
	public String addcategorytouser (
			HttpSession session
			, @RequestParam("categories") Long categoryId
	) {
		Long userid =  (Long) session.getAttribute("userid");
		User user = userService.findById(userid);
		Category newcat = categoryService.findCategoryById(categoryId) ;
		userService.addCategory(user, newcat);
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
					, RedirectAttributes ra
	) {
		System.out.println("hit post create!");
		if(result.hasErrors()) {
			List<ObjectError> x = result.getAllErrors();
				System.out.println("errors found..."+result.getErrorCount());
				System.out.println(x);
		return "redirect:/show/"+ threadid;
		}
		System.out.println("checking if the video exists in thread...");
		boolean canbeadded = postService.validateVid(threadid, post.getV_id());
		if(canbeadded) {
			System.out.println("sending to PostService...");
			Post newpost= postService.createPost(post);		
				threadService.calculateThreadRating(newpost.getThread());			
				System.out.println("result : " + newpost + " Rating set to : " + newpost.getThread().getRating());
		}
		else {
			ra.addFlashAttribute("existserror" , "video already exists in thread");
		}
		return"redirect:/show/"+ threadid;
	}
	
}
