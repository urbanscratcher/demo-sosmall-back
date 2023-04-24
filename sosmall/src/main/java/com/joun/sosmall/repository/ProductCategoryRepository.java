package com.joun.sosmall.repository;

import java.util.List;
import java.util.Optional;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;

import com.joun.sosmall.entity.ProductCategory;

public interface ProductCategoryRepository extends JpaRepository<ProductCategory, Integer> {

  @Query("SELECT distinct p from ProductCategory p left join fetch p.parent pr where p.id = :id order by p.listOrder")
  public Optional<ProductCategory> findById(@Param("id") int id);

  @Query("SELECT node FROM ProductCategory node WHERE node.id NOT IN (SELECT DISTINCT child.id FROM ProductCategory node, IN(node.children) child) and node.parent is null order by node.listOrder")
  public List<ProductCategory> findAll();
}
