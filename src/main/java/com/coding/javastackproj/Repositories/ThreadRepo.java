package com.coding.javastackproj.Repositories;

import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.coding.javastackproj.Models.Thread;

public interface ThreadRepo extends CrudRepository<Thread, Long>{
	List<Thread> findAll();
	
	@Query(value= "select * from thread_categories "
			+ "where category_id in "
			+ "(select distinct category_id from user_interests where user_id = ?1)"
			+ "order by category_id", nativeQuery=true)
	List<Object> findAllInterestingThreads();
}
