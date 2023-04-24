package com.joun.sosmall.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.joun.sosmall.entity.Product;

public interface ProductRepository extends JpaRepository<Product, Integer> {

  // @Query("SELECT distinct p from Product p left join fetch p.stocks where p.id
  // = :id")
  // public Optional<Product> findById(@Param("id") int id);

  // @Query("SELECT p from Product p left join fetch p.thumbnails left join fetch
  // p.stocks")
  // public List<Product> findAll();
}
