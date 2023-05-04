package com.joun.sosmall.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joun.sosmall.common.exception.NotFoundException;
import com.joun.sosmall.dtoRequest.InterestCreateDto;
import com.joun.sosmall.dtoResponse.ProductBasicDto;
import com.joun.sosmall.entity.Interest;
import com.joun.sosmall.entity.Product;
import com.joun.sosmall.repository.InterestRepository;
import com.joun.sosmall.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class InterestServiceImpl {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final InterestRepository repo;
  private final ProductRepository productRepo;

  public void create(InterestCreateDto dto) throws Exception {
    Product product = productRepo.findById(dto.getProductId()).orElseThrow(() -> new NotFoundException("no product"));
    dto.setProduct(product);

    repo.save(dto.toEntity());
  }

  public void delete(InterestCreateDto dto) throws Exception {
    Product product = productRepo.findById(dto.getProductId()).orElseThrow(() -> new NotFoundException("no product"));
    dto.setProduct(product);

    repo.delete(dto.toEntity());
  }

  public List<ProductBasicDto> findByMemberId(int memberId) throws Exception {
    List<Interest> interests = repo.findByIdMemberId(memberId);
    if (interests.isEmpty() && interests.size() == 0) {
      return new ArrayList<>();
    }

    return interests.stream().map((o) -> new ProductBasicDto(o.getId().getProduct())).collect(Collectors.toList());
  }

  public Long findByProductId(int productId) throws Exception {
    return repo.countByIdProductId(productId);
  }

}
