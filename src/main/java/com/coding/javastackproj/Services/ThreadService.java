package com.coding.javastackproj.Services;

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
//	Create
	public Thread createThread(User creator , Thread thread) {		
		thread.setCreator(creator);
		System.out.println("set creator success");
		threadRepo.save(thread);
		System.out.println("saved created thread..returning to controller now ");
		return thread; 
	}
	
	
//	Read
//	Update
//	Destroy
	
	
}
