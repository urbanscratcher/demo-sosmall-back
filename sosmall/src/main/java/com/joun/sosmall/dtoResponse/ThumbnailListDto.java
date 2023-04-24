package com.joun.sosmall.dtoResponse;

import com.joun.sosmall.entity.Thumbnail;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ThumbnailListDto {
  private int id;
  private String url;

  public ThumbnailListDto(Thumbnail thumbnail) {
    this.id = thumbnail.getId();
    this.url = thumbnail.getUrl();
  }

}
