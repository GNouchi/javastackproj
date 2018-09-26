package com.coding.javastackproj.Models;

import java.util.Date;
import java.util.List;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.JoinTable;
import javax.persistence.ManyToMany;
import javax.persistence.PrePersist;
import javax.persistence.Table;
import javax.validation.constraints.Size;

@Entity
@Table(name="categories")
public class Category {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=1, max= 50)
	private String category_name;
	
	
	@ManyToMany(fetch= FetchType.LAZY)
	@JoinTable(
			name="thread_categories",
			joinColumns= @JoinColumn(name="category_id"),
			inverseJoinColumns = @JoinColumn(name="thread_id")			
	)
	private List<Thread> threads; 

	@ManyToMany(fetch=FetchType.LAZY)
	@JoinTable(
			name = "user_interests",
			joinColumns = @JoinColumn( name = "category_id" ),
			inverseJoinColumns = @JoinColumn( name = "user_id" )
	)
	private List<Category> user_interests;	


	
	@Column(updatable=false)
    private Date createdAt;
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    
    
//	Contructor    
	public Category() {
		super();
	}
//	Getters and Setters	
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getCategory_name() {
		return category_name;
	}
	public void setCategory_name(String category_name) {
		this.category_name = category_name;
	}
	public List<Thread> getThreads() {
		return threads;
	}
	public void setThreads(List<Thread> threads) {
		this.threads = threads;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}

	public List<Category> getUser_interests() {
		return user_interests;
	}

	public void setUser_interests(List<Category> user_interests) {
		this.user_interests = user_interests;
	}	

    
}
