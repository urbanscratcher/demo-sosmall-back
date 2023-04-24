package com.joun.sosmall.serviceImpl;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joun.sosmall.dtoRequest.ThumbnailCreateDto;
import com.joun.sosmall.repository.ThumbnailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThumbnailServiceImpl {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final ThumbnailRepository repo;

  public void create(ThumbnailCreateDto dto) {
    repo.save(dto.toEntity());
  }

}
