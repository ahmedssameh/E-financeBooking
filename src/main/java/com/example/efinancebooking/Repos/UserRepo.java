package com.example.efinancebooking.Repos;

import com.example.efinancebooking.Model.User;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends CrudRepository<User,Integer> {

    @Query("select U from User U where U.uid=?1")
    public User findUserByUid(int uid);
}
