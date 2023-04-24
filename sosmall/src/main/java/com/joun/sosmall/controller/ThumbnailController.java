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

import com.joun.sosmall.dtoRequest.ThumbnailCreateDto;
import com.joun.sosmall.dtoResponse.ThumbnailDetailDto;
import com.joun.sosmall.serviceImpl.ThumbnailServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api/products")
public class ThumbnailController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final ThumbnailServiceImpl service;

  @PostMapping("/{productId}/thumbnails")
  public void create(@PathVariable int productId, @RequestBody ThumbnailCreateDto req) throws Exception {
    req.setProductId(productId);
    service.create(req);
  }

  @GetMapping("/{productId}/thumbnails")
  public List<ThumbnailDetailDto> findById(@PathVariable int productId) throws Exception {
    return service.findByProductId(productId);
  }

  @PutMapping("/{productId}/thumbnails")
  public void update(@PathVariable int productId, @RequestBody List<ThumbnailCreateDto> req) throws Exception {
    service.update(productId, req);
  }

  @DeleteMapping("/{productId}/thumbnails/{id}")
  public void delete(@PathVariable int productId, @PathVariable int id) throws Exception {
    service.delete(id);
  }

}
