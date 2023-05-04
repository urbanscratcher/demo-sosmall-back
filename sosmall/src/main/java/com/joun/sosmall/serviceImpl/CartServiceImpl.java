package com.joun.sosmall.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joun.sosmall.common.exception.NotFoundException;
import com.joun.sosmall.dtoRequest.CartCreateDto;
import com.joun.sosmall.dtoResponse.CartListDto;
import com.joun.sosmall.entity.Cart;
import com.joun.sosmall.entity.Stock;
import com.joun.sosmall.repository.CartRepository;
import com.joun.sosmall.repository.StockRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class CartServiceImpl {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final CartRepository repo;
  private final StockRepository stockRepo;

  public void create(CartCreateDto dto) throws Exception {
    Stock stock = stockRepo.findById(dto.getStockId()).orElseThrow(() -> new NotFoundException("no stock"));
    dto.setStock(stock);
    repo.save(dto.toEntity());
  }

  public void remove(CartCreateDto dto) throws Exception {
    Cart cart = dto.toEntity();
    repo.delete(cart);
  }

  public void removeAll(int memberId) throws Exception {
    List<Cart> carts = repo.findByMemberId(memberId);
    repo.deleteAllInBatch(carts);
  }

  public List<CartListDto> findByMemberId(int memberId) throws Exception {
    List<Cart> carts = repo.findByMemberId(memberId);
    if (carts.isEmpty() && carts.size() == 0) {
      return new ArrayList<>();
    }

    return carts.stream().map(o -> new CartListDto(o)).collect(Collectors.toList());
  }
}
