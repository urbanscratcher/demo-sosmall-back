package com.joun.sosmall.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joun.sosmall.dtoRequest.ProductCategoryCreateDto;
import com.joun.sosmall.dtoResponse.ProductCategoryDetailDto;
import com.joun.sosmall.entity.ProductCategory;
import com.joun.sosmall.serviceImpl.ProductCategoryServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductCategoryController {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final ProductCategoryServiceImpl service;

  @PostMapping("/product-categories")
  public void create(@RequestBody ProductCategoryCreateDto dto) throws Exception {
    this.service.create(dto);
  }

  @GetMapping("/product-categories/{id}")
  public ProductCategoryDetailDto findById(@PathVariable int id) throws Exception {
    return new ProductCategoryDetailDto(this.service.findById(id));
  }

  @GetMapping("/product-categories")
  public List<ProductCategory> findById() throws Exception {
    return this.service.find();
  }

}
