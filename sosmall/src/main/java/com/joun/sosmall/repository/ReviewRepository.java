package com.joun.sosmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joun.sosmall.entity.Review;

public interface ReviewRepository extends JpaRepository<Review, Integer> {

}
