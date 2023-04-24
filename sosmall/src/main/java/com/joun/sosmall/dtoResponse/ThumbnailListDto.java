package com.joun.sosmall.dtoResponse;

import com.joun.sosmall.entity.Thumbnail;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ThumbnailListDto {
  private String url;
  private int listOrder;

  public ThumbnailListDto(Thumbnail thumbnail) {
    this.url = thumbnail.getUrl();
    this.listOrder = thumbnail.getListOrder();
  }

}
