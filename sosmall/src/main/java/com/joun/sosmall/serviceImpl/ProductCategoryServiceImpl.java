package com.joun.sosmall.serviceImpl;

import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joun.sosmall.common.exception.LogicalConflictException;
import com.joun.sosmall.common.exception.NotFoundException;
import com.joun.sosmall.dtoRequest.ProductCategoryCreateDto;
import com.joun.sosmall.dtoResponse.ProductCategoryDetailDto;
import com.joun.sosmall.entity.ProductCategory;
import com.joun.sosmall.repository.ProductCategoryRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductCategoryServiceImpl {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final ProductCategoryRepository repo;

  public void create(ProductCategoryCreateDto dto) throws Exception {
    if (dto.getParentId() > 0) {
      dto.setParent(this.repo.findById(dto.getParentId()).orElseThrow(() -> new LogicalConflictException()));
    }
    repo.save(dto.toEntity());
  }

  public ProductCategory findById(int id) throws Exception {
    return repo.findById(id).orElseThrow(() -> new NotFoundException());
  }

  public List<ProductCategoryDetailDto> find() throws Exception {
    return repo.findAll().stream()
        .map(ProductCategoryDetailDto::new)
        .collect(Collectors.toList());
  }

}
