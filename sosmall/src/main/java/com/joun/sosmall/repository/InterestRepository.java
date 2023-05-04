package com.joun.sosmall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.query.Param;

import com.joun.sosmall.entity.Interest;

public interface InterestRepository extends JpaRepository<Interest, Integer> {

  public List<Interest> findByIdMemberId(@Param("memberId") int memberId);

  public Long countByIdProductId(@Param("productId") int productId);
}
