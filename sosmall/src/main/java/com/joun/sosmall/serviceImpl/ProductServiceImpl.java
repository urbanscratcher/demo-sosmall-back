package com.joun.sosmall.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joun.sosmall.common.exception.LogicalConflictException;
import com.joun.sosmall.common.exception.NotFoundException;
import com.joun.sosmall.dtoRequest.ProductCreateDto;
import com.joun.sosmall.dtoResponse.ProductDetailDto;
import com.joun.sosmall.dtoResponse.ProductListDto;
import com.joun.sosmall.entity.Product;
import com.joun.sosmall.entity.ProductCategory;
import com.joun.sosmall.repository.ProductCategoryRepository;
import com.joun.sosmall.repository.ProductRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ProductServiceImpl {

  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final ProductRepository repo;
  private final ProductCategoryRepository productCategoryRepo;

  public void create(ProductCreateDto dto) throws Exception {

    ProductCategory productCategory = productCategoryRepo.findById(dto.getProductCategoryId())
        .orElseThrow(() -> new LogicalConflictException());

    dto.setProductCategory(productCategory);

    repo.save(dto.toEntity());
  }

  public ProductDetailDto findById(int id) throws Exception {
    Product product = repo.findById(id).orElseThrow(() -> new NotFoundException());
    return new ProductDetailDto(product);
  }

  public List<ProductListDto> find() throws Exception {
    List<Product> list = repo.findAll();
    if (!list.isEmpty() && list.size() != 0) {
      return list.stream().map((o) -> new ProductListDto(o)).collect(Collectors.toList());
    }
    return new ArrayList<ProductListDto>();

  }

  public void update(int id, ProductCreateDto dto) throws Exception {
    Product product = repo.findById(id).orElseThrow(() -> new NotFoundException());

    if (dto.getProductCategoryId() != null) {
      ProductCategory productCategory = productCategoryRepo.findById(dto.getProductCategoryId())
          .orElseThrow(() -> new LogicalConflictException());
      dto.setProductCategory(productCategory);
    }

    product.setUpdate(dto);
    repo.save(product);
  }

  public void delete(int id) throws Exception {
    Product product = repo.findById(id).orElseThrow(() -> new NotFoundException());
    repo.delete(product);
  }

}
