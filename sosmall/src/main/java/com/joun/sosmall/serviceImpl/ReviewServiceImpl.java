package com.joun.sosmall.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joun.sosmall.common.exception.NotFoundException;
import com.joun.sosmall.dtoRequest.ReviewCreateDto;
import com.joun.sosmall.dtoResponse.ReviewDetailDto;
import com.joun.sosmall.dtoResponse.ReviewListDto;
import com.joun.sosmall.entity.Review;
import com.joun.sosmall.repository.ReviewRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ReviewServiceImpl {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final ReviewRepository repo;

  public void create(ReviewCreateDto dto) throws Exception {
    repo.save(dto.toEntity());
  }

  public ReviewDetailDto findById(int id) throws Exception {
    Review review = repo.findById(id).orElseThrow(() -> new NotFoundException());
    return new ReviewDetailDto(review);
  }

  public void update(int id, ReviewCreateDto dto) throws Exception {
    Review review = repo.findById(id).orElseThrow(() -> new NotFoundException());
    review.setUpdate(dto);
    repo.save(review);
  }

  public List<ReviewListDto> find() throws Exception {
    return repo.findAll().stream().map((o) -> new ReviewListDto(o)).collect(Collectors.toList());
  }

  public void delete(int id) throws Exception {
    Review review = repo.findById(id).orElseThrow(() -> new NotFoundException());
    repo.delete(review);
  }

}
