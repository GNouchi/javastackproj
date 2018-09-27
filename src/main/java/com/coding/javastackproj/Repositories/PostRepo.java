package com.coding.javastackproj.Repositories;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.coding.javastackproj.Models.Post;

public interface PostRepo extends CrudRepository<Post,Long>{
	List<Post> findAll();	

//	@Query(value = "select * from posts where thread_id =?1", nativeQuery= True)
//	List<Post> findAllPostsByThreadId(Long id);	
	
	@Query(value="select distinct thread_id from posts where v_id like '?1' ", nativeQuery=true)
	List<Long> findByVID(String vid);
	
	
	
}

