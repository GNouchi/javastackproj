package com.coding.javastackproj.Services;

import java.util.List;
import java.util.Optional;

import org.springframework.stereotype.Service;

import com.coding.javastackproj.Models.Category;
import com.coding.javastackproj.Repositories.CategoryRepo;

@Service
public class CategoryService {
	private final CategoryRepo categoryRepo;

	public CategoryService(CategoryRepo categoryRepo) {
		this.categoryRepo = categoryRepo;
	}
//	Find all Categories
	public List<Category> findAllCategories(){
		return categoryRepo.findAll();		
	}
//	Find one Category by ID
	public Category findCategoryById(Long id) {
		Optional<Category> result = categoryRepo.findById(id);		
		if(result.isPresent()) {
			return result.get();
		}
		else return null;
	}
// Category exists by Name
	public boolean categoryExists(String test) {
		List<Category> allCats = findAllCategories();
		for( int i = 0; i < allCats.size() ; i++ ) {
			if(allCats.get(i).getCategory_name().equals(test)) {
				return true;
			}			
		}
		return false;		
	}
	
	
//	Create (returns null or category)
	public Category createCategory(String category_name) {		
		if(!categoryExists(category_name)) {
			Category newCat = new Category();
			newCat.setCategory_name(category_name);
			return categoryRepo.save(newCat);			
		}
		else return null;
	}
	
//	Read
//	Update
//	Destroy

	
}
