package com.coding.javastackproj.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.coding.javastackproj.Models.Post;
import com.coding.javastackproj.Models.Thread;
import com.coding.javastackproj.Repositories.ThreadRepo;

@Service
public class ThreadService {
	private final ThreadRepo threadRepo;
	public ThreadService(ThreadRepo threadRepo) {
		this.threadRepo = threadRepo;
	}

//	Find Thread by ID
	
	public Thread findThreadById(Long id) {
		Optional<Thread> result = threadRepo.findById(id);
		if(result.isPresent()) {
			return result.get();			
		}
		else return null;
	}	

//	allThreads (temporary)
	public List<Thread> findAllThreads(){
		return threadRepo.findAll();
	}

//	first video by thread
	
		
	
//	Create
	public Thread createThread( Thread thread) {		
		threadRepo.save(thread);
				System.out.println("saved created thread..returning to controller now ");
		return thread; 
	}

//	Calculate Thread Rating 
	public void calculateThreadRating(Thread thread) {
		Double sum = (double) 0;
		List<Post> threadPosts = thread.getPosts();
		for(Post post: threadPosts) {
			sum=sum + (Double) post.getPersonal_rating();
		}
		sum=sum/(double) threadPosts.size();
		thread.setRating(sum);
	}
	
//	Read
//	Update
//	Destroy
	
	
}
