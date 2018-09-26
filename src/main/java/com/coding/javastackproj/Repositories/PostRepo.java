package com.coding.javastackproj.Repositories;
import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.coding.javastackproj.Models.Post;

public interface PostRepo extends CrudRepository<Post,Long>{
	List<Post> findAll();	
//	@Query(value = "select * from posts where thread_id =?1", nativeQuery= True)
//	List<Post> findAllPostsByThreadId(Long id);	
	
}

