package com.example.efinancebooking.Repos;

import com.example.efinancebooking.Model.Review;
import org.springframework.data.jpa.repository.JpaRepository;

public interface ReviewRepo extends JpaRepository<Review,Integer> {
}
