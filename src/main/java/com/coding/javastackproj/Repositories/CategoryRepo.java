package com.coding.javastackproj.Repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.coding.javastackproj.Models.Category;

public interface CategoryRepo extends CrudRepository <Category, Long>{
	List<Category> findAll();
	
}
