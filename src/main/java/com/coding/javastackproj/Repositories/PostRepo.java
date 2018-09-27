package com.coding.javastackproj.Repositories;
import java.math.BigInteger;
import java.util.List;

import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;

import com.coding.javastackproj.Models.Post;

public interface PostRepo extends CrudRepository<Post,Long>{
	List<Post> findAll();	
	
	@Query(value="select distinct thread_id from posts where thread_id = ?1 and v_id =?2", nativeQuery=true)
	List<BigInteger> findByThreadAndVId(Long threadid,String vid);

	@Query(value="SELECT * FROM posts p join thread_categories tc on p.thread_id = tc.thread_id\r\n" + 
			"where category_id = 1 order by created_at desc limit 1", nativeQuery= true)
	Post findLastPostByCategoryId(Long categoryid);
	
	
}

