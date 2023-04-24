package com.joun.sosmall.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.joun.sosmall.entity.Stock;

public interface StockRepository extends JpaRepository<Stock, Integer> {

}
