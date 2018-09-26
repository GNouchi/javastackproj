package com.coding.javastackproj.Repositories;

import java.util.List;
import com.coding.javastackproj.Models.Thread;
import org.springframework.data.repository.CrudRepository;

public interface ThreadRepo extends CrudRepository<Thread, Long>{
	List<Thread> findAll();
}
