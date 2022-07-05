package com.example.efinancebooking.Repos;

import com.example.efinancebooking.Model.User;
import org.springframework.data.repository.CrudRepository;

public interface UserRepo extends CrudRepository<User,Integer> {
}
