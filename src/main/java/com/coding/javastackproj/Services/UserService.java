package com.coding.javastackproj.Services;

import java.util.List;
import java.util.Optional;

import org.mindrot.jbcrypt.BCrypt;
import org.springframework.stereotype.Service;

import com.coding.javastackproj.Models.Category;
import com.coding.javastackproj.Models.User;
import com.coding.javastackproj.Repositories.UserRepo;

@Service
public class UserService {
	private final UserRepo userRepo;

	public UserService(UserRepo userRepo) {
		this.userRepo = userRepo;
	}
	
//	find all users
	public List<User> findAllUsers(){
		return userRepo.findAll();
	}
// find user by id
	public User findById(Long id) {
		Optional<User> x = userRepo.findById(id);
		if(x.isPresent()) {
			return x.get();
		}
		else return null;
	}
// find user by email
	public User findByUsername(String username) {
		return userRepo.findByUsername(username);
	}
	
//	register user
	public User registerUser(User user) {
		String hashed = BCrypt.hashpw(user.getPassword(), BCrypt.gensalt());
		user.setPassword(hashed);
        return userRepo.save(user);
	}
	
// authenticate user
	public boolean authenticateUser(String username, String password) {
		User user = userRepo.findByUsername(username);
		if (user == null) {
				return false;}
		if(BCrypt.checkpw(password, user.getPassword())) {
			return true;
				}
		return false;
	}
//	save user (for modification)
	public void saveUser(User user) {
		System.out.println("saving user!");
		userRepo.save(user);
	}
// 	add category to user_interest
	public void addCategory(User user, Category newcat) {
		List<Category> user_interests = user.getUser_interests();
			System.out.println("Am I adding? : " + !user_interests.contains(newcat) );
		if(!user_interests.contains(newcat)) {
			user_interests.add(newcat);			
			userRepo.save(user);
				System.out.println("adding");
		}
		else {
				System.out.println("failed to add");
		}
		System.out.println("interests is now : "+ user.getUser_interests());
	}
	
	
}
