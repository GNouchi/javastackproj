package com.coding.javastackproj.Services;

import java.util.List;

import org.springframework.stereotype.Service;

import com.coding.javastackproj.Models.Post;
import com.coding.javastackproj.Repositories.PostRepo;

@Service
public class PostService {
	private final PostRepo postRepo;

	public PostService(PostRepo postRepo) {
		this.postRepo = postRepo;
	}
	
	public List<Post> findAllPosts(){
		return postRepo.findAll();
	}
	
//	public List<Post> findAllPostsByThreadID(Long id){
//		return postRepo.findAllPostsByThreadId(id);
//	}
	
//	Create	
	public Post createPost(Post post) {
		postRepo.save(post);
		return post;
	}
//	Read
//	Update
//	Destroy
	
	
}
