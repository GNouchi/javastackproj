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
// Find post by V_id
	public boolean validateVid(Long threadid, String vid) {
		List<Long> matches= postRepo.findByVID(vid);
		for (Long x:matches) {
			if(x == threadid) 
			{
				return false;
			}
		}
		return true;
	}
	
//	public List<Post> findAllPostsByThreadID(Long id){
//		return postRepo.findAllPostsByThreadId(id);
//	}
	
//	Create	Post
	public Post createPost(Post post) {
		boolean notinthread = validateVid(post.getThread().getId(), post.getV_id());
		return (notinthread) ? 	postRepo.save(post) : null;
	}
	
//	Read
//	Update
//	Destroy
	
	
}
