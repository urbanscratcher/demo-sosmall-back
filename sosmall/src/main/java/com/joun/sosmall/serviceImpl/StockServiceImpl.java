package com.joun.sosmall.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joun.sosmall.common.exception.LogicalConflictException;
import com.joun.sosmall.common.exception.NotFoundException;
import com.joun.sosmall.dtoRequest.StockCreateDto;
import com.joun.sosmall.dtoResponse.StockDetailDto;
import com.joun.sosmall.entity.Product;
import com.joun.sosmall.entity.Stock;
import com.joun.sosmall.repository.ProductRepository;
import com.joun.sosmall.repository.StockRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class StockServiceImpl {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final StockRepository repo;
  private final ProductRepository productRepo;

  public void create(StockCreateDto dto) throws Exception {

    productRepo.findById(dto.getProductId()).orElseThrow(() -> new LogicalConflictException());

    dto.setProduct(Product.builder().id(dto.getProductId()).build());

    repo.save(dto.toEntity());
  }

  public void update(int id, StockCreateDto dto) throws Exception {

    Stock stock = repo.findById(id).orElseThrow(() -> new NotFoundException());

    if (dto.getProductId() != stock.getProduct().getId()) {
      productRepo.findById(dto.getProductId()).orElseThrow(() -> new LogicalConflictException());
    }

    dto.setProduct(Product.builder().id(dto.getProductId()).build());

    stock.setUpdate(dto);

    repo.save(stock);
  }

  public void decreaseQuantity(int id, StockCreateDto dto) throws Exception {
    Stock stock = repo.findById(id).orElseThrow(() -> new NotFoundException());
    stock.decreaseQuantity(dto);
    repo.save(stock);
  }

  public StockDetailDto findById(int id) throws Exception {
    Stock stock = repo.findById(id).orElseThrow(() -> new NotFoundException());
    return new StockDetailDto(stock);
  }

  public void delete(int id) throws Exception {
    Stock stock = repo.findById(id).orElseThrow(() -> new NotFoundException());
    repo.delete(stock);
  }

  public List<StockDetailDto> findAll() throws Exception {
    List<Stock> list = repo.findAll();

    if (list.isEmpty() && list.size() == 0) {
      return new ArrayList<>();
    }

    return list.stream()
        .map((o) -> new StockDetailDto(o))
        .collect(Collectors.toList());
  }
}
