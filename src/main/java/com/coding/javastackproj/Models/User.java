package com.coding.javastackproj.Models;


import java.util.ArrayList;
import java.util.List;

import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.OneToMany;
import javax.persistence.Table;
import javax.validation.constraints.Size;

import org.springframework.data.annotation.Transient;

@Entity
@Table(name="users")
public class User {
		@Id
	    @GeneratedValue(strategy=GenerationType.IDENTITY)
	    private Long id;
		private Double temperature = (double) 0;
		
		@Size(min=1, max=30)
		private String username;
		
		@Size(min=5, message="Password must be between greater than 5 characters")
		private String password; 
		
		@Transient 
		private String passwordConfirmation;
		
		@OneToMany(mappedBy="creator", fetch = FetchType.LAZY)
		private List<Thread> createdThreads = new ArrayList<Thread>();
		
		@ManyToMany(fetch=FetchType.LAZY)
		@JoinTable(
				name = "user_interests",
				joinColumns = @JoinColumn( name = "user_id" ),
				inverseJoinColumns = @JoinColumn( name = "category_id" )
		)
		private List<Category> user_interests;	
		
		@OneToMany(mappedBy="post_owner", fetch = FetchType.LAZY)
		private List<Post> created_posts;
		
		
		
		
//	Constructor		
		public User() {
		}
		
		
//	Getters and setters
		public Long getId() {
			return id;
		}

		public void setId(Long id) {
			this.id = id;
		}

		public String getUsername() {
			return username;
		}

		public void setUsername(String username) {
			this.username = username;
		}

		public String getPassword() {
			return password;
		}

		public void setPassword(String password) {
			this.password = password;
		}

		public String getPasswordConfirmation() {
			return passwordConfirmation;
		}

		public void setPasswordConfirmation(String passwordConfirmation) {
			this.passwordConfirmation = passwordConfirmation;
		}

		public List<Thread> getCreatedThreads() {
			return createdThreads;
		}

		public void setCreatedThreads(List<Thread> createdThreads) {
			this.createdThreads = createdThreads;
		}

		public Double getTemperature() {
			return temperature;
		}

		public void setTemperature(Double temperature) {
			this.temperature = temperature;
		}
		public List<Category> getUser_interests() {
			return user_interests;
		}
		public void setUser_interests(List<Category> user_interests) {
			this.user_interests = user_interests;
		}

		public List<Post> getCreated_posts() {
			return created_posts;
		}
		public void setCreated_posts(List<Post> created_posts) {
			this.created_posts = created_posts;
		}
		
		
		
	
}
