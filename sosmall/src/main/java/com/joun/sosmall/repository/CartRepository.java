package com.joun.sosmall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.joun.sosmall.entity.Cart;

public interface CartRepository extends JpaRepository<Cart, Integer> {

  @Query("SELECT c FROM Cart c where c.id.memberId = :memberId")
  public List<Cart> findByMemberId(@Param("memberId") int memberId);

}
