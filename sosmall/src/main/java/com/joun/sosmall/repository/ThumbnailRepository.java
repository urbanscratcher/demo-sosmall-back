package com.joun.sosmall.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joun.sosmall.entity.Thumbnail;

public interface ThumbnailRepository extends JpaRepository<Thumbnail, Integer> {

  public List<Thumbnail> findByProductId(int productId);

}
