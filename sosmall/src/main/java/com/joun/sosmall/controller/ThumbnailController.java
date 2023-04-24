package com.joun.sosmall.controller;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.joun.sosmall.dtoRequest.ThumbnailCreateDto;
import com.joun.sosmall.serviceImpl.ThumbnailServiceImpl;

import lombok.RequiredArgsConstructor;

@RestController
@RequiredArgsConstructor
@RequestMapping("/api")
public class ThumbnailController {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final ThumbnailServiceImpl service;

  @PostMapping("/thumbnails")
  public void create(@RequestBody ThumbnailCreateDto req) throws Exception {
    service.create(req);
  }

}
