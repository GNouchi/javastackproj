package com.coding.javastackproj.Services;

import java.util.Optional;

import org.springframework.stereotype.Service;

import com.coding.javastackproj.Models.Thread;
import com.coding.javastackproj.Models.User;
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
	
	
//	Create
	public Thread createThread( Thread thread) {		
		threadRepo.save(thread);
				System.out.println("saved created thread..returning to controller now ");
		return thread; 
	}
	
	
//	Read
//	Update
//	Destroy
	
	
}
