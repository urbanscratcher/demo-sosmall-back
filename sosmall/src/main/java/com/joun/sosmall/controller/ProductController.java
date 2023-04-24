package com.joun.sosmall.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joun.sosmall.dtoRequest.ProductCreateDto;
import com.joun.sosmall.dtoResponse.ProductDetailDto;
import com.joun.sosmall.dtoResponse.ProductListDto;
import com.joun.sosmall.serviceImpl.ProductServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ProductController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final ProductServiceImpl service;

  @PostMapping("/products")
  public void create(@RequestBody ProductCreateDto req) throws Exception {
    service.create(req);
  }

  @GetMapping("/products/{id}")
  public ProductDetailDto findById(@PathVariable int id) throws Exception {
    return service.findById(id);
  }

  @GetMapping("/products")
  public List<ProductListDto> find() throws Exception {
    return service.find();
  }

  @PutMapping("/products/{id}")
  public void update(@PathVariable int id, @RequestBody ProductCreateDto req) throws Exception {
    service.update(id, req);
  }

  @DeleteMapping("/products/{id}")
  public void delete(@PathVariable int id) throws Exception {
    service.delete(id);
  }

}
