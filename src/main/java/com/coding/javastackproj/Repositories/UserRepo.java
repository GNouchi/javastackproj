package com.coding.javastackproj.Repositories;

import java.util.List;
import org.springframework.data.repository.CrudRepository;
import com.coding.javastackproj.Models.User;

public interface UserRepo extends CrudRepository<User,Long>{
	List<User> findAll();
    User findByUsername(String userName);

}
