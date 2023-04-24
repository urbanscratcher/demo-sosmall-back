package com.joun.sosmall.serviceImpl;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Service;

import com.joun.sosmall.common.exception.NotFoundException;
import com.joun.sosmall.dtoRequest.ThumbnailCreateDto;
import com.joun.sosmall.dtoResponse.ThumbnailDetailDto;
import com.joun.sosmall.entity.Product;
import com.joun.sosmall.entity.Thumbnail;
import com.joun.sosmall.repository.ProductRepository;
import com.joun.sosmall.repository.ThumbnailRepository;

import lombok.RequiredArgsConstructor;

@Service
@RequiredArgsConstructor
public class ThumbnailServiceImpl {
  private final Logger logger = LoggerFactory.getLogger(this.getClass());
  private final ThumbnailRepository repo;
  private final ProductRepository productRepo;

  public void create(ThumbnailCreateDto dto) throws Exception {
    Product product = productRepo.findById(dto.getProductId()).orElseThrow(() -> new NotFoundException("no product"));
    dto.setProduct(product);
    repo.save(dto.toEntity());
  }

  public List<ThumbnailDetailDto> findByProductId(int productId) throws Exception {
    Product product = productRepo.findById(productId).orElseThrow(() -> new NotFoundException("no product"));

    List<ThumbnailDetailDto> result = new ArrayList<>();
    List<Thumbnail> thumbnails = product.getThumbnails();
    if (!thumbnails.isEmpty() && thumbnails.size() != 0) {
      thumbnails.stream().map((o) -> result.add(new ThumbnailDetailDto(o)))
          .collect(Collectors.toList());
    }
    return result;
  }

  public void update(int productId, List<ThumbnailCreateDto> req) throws Exception {
    Product product = productRepo.findById(productId).orElseThrow(() -> new NotFoundException("no product"));

    List<Thumbnail> thumbnails = repo.findByProductId(productId);

    if (!thumbnails.isEmpty() && thumbnails.size() != 0) {
      for (Thumbnail t : thumbnails) {
        repo.deleteById(t.getId());
      }
    }

    req.stream().forEach((o) -> {
      o.setProduct(product);
      repo.save(o.toEntity());
    });
  }

  public void delete(int id) throws Exception {
    repo.findById(id).orElseThrow(() -> new NotFoundException());
    repo.deleteById(id);
  }

}
