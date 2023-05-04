package com.joun.sosmall.controller;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PatchMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joun.sosmall.dtoRequest.StockCreateDto;
import com.joun.sosmall.dtoResponse.StockDetailDto;
import com.joun.sosmall.dtoResponse.StockListDto;
import com.joun.sosmall.serviceImpl.StockServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class StockController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final StockServiceImpl service;

  @PostMapping("/stocks")
  public void create(@RequestBody StockCreateDto req) throws Exception {
    service.create(req);
  }

  @PutMapping("/stocks/{id}")
  public void update(@PathVariable int id, @RequestBody StockCreateDto req) throws Exception {
    service.update(id, req);
  }

  @PatchMapping("/stocks/{id}")
  public void decreaseQuantity(@PathVariable int id, @RequestBody StockCreateDto req) throws Exception {
    service.decreaseQuantity(id, req);
  }

  @GetMapping("/stocks/{id}")
  public StockListDto findById(@PathVariable int id) throws Exception {
    return service.findById(id);
  }

  @DeleteMapping("/stocks/{id}")
  public void delete(@PathVariable int id) throws Exception {
    service.delete(id);
  }

  @GetMapping("/stocks")
  public List<StockDetailDto> find() throws Exception {
    return service.findAll();
  }

}
