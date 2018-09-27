package com.coding.javastackproj.Services;

import java.math.BigInteger;
import java.util.List;

import org.springframework.stereotype.Service;

import com.coding.javastackproj.Models.Category;
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
// Find post by V_id
	public boolean validateVid(Long threadid, String vid) {
		List<BigInteger> matches= postRepo.findByThreadAndVId(threadid, vid);

		for (BigInteger x:matches) {
			if(x.longValue() == threadid) 
				{
					System.out.println("video can be added : false");
					return false;
				}
		}
		System.out.println("video can be added : true");
		return true;
	}	
	
//	Create	Post
	public Post createPost(Post post) {
		return postRepo.save(post);
	}
//	Find last created post by Category
	public String findLastVidByCategoryId(Long categoryid) {
		System.out.println("found : "+ postRepo.findLastPostByCategoryId(categoryid).getV_id());
			return postRepo.findLastPostByCategoryId(categoryid).getV_id();
	}
	
	
//	Read
//	Update
//	Destroy
	
	
}
