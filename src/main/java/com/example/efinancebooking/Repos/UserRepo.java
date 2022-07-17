package com.example.efinancebooking.Repos;

import com.example.efinancebooking.Model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface UserRepo extends JpaRepository<User,Integer> {

    @Query("select U from User U where U.uid=?1")
    public User findUserByUid(int uid);

    @Query("select U from User U where U.Username=?1")
    public User findUserByName(String username);
}
