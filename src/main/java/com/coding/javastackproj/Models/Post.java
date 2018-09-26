package com.coding.javastackproj.Models;

import java.util.Date;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.PrePersist;
import javax.persistence.PreUpdate;
import javax.persistence.Table;
import javax.validation.constraints.Max;
import javax.validation.constraints.Min;
import javax.validation.constraints.Size;

@Entity
@Table(name="posts")
public class Post {
	@Id
	@GeneratedValue(strategy=GenerationType.IDENTITY)
	private Long id;
	
	@Size(min=1, max= 300)
	private String comment;	
	
	@Size(max=15)
	private String v_id;
	
	@Max(10)
	@Min(-10)
	private Double personal_rating;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="post_owner_id")
	private User post_owner;
	
	@ManyToOne(fetch=FetchType.LAZY)
	@JoinColumn(name="thread_id")
	private Thread thread;
	
	
    @Column(updatable=false)
    private Date createdAt;
    private Date updatedAt;	    
    
    @PrePersist
    protected void onCreate(){
        this.createdAt = new Date();
    }
    @PreUpdate
    protected void onUpdate(){
        this.updatedAt = new Date();
    }
    
//	Constructor    
	public Post() {
	}
	
	
//	Getters and Setters			
	public Long getId() {
		return id;
	}
	public void setId(Long id) {
		this.id = id;
	}
	public String getComment() {
		return comment;
	}
	public void setComment(String comment) {
		this.comment = comment;
	}
	public String getV_id() {
		return v_id;
	}
	public void setV_id(String v_id) {
		this.v_id = v_id;
	}
	public Double getPersonal_rating() {
		return personal_rating;
	}
	public void setPersonal_rating(Double personal_rating) {
		this.personal_rating = personal_rating;
	}
	public User getPost_owner() {
		return post_owner;
	}
	public void setPost_owner(User post_owner) {
		this.post_owner = post_owner;
	}
	public Thread getThread() {
		return thread;
	}
	public void setThread(Thread thread) {
		this.thread = thread;
	}
	public Date getCreatedAt() {
		return createdAt;
	}
	public void setCreatedAt(Date createdAt) {
		this.createdAt = createdAt;
	}
	public Date getUpdatedAt() {
		return updatedAt;
	}
	public void setUpdatedAt(Date updatedAt) {
		this.updatedAt = updatedAt;
	}	
		
}
