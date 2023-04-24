package com.joun.sosmall.dtoResponse;

import java.util.Date;

import com.joun.sosmall.entity.Thumbnail;

import lombok.Getter;
import lombok.NoArgsConstructor;

@Getter
@NoArgsConstructor
public class ThumbnailDetailDto extends ThumbnailListDto {
  private Date regAt;
  private Date modAt;

  public ThumbnailDetailDto(Thumbnail thumbnail) {
    super(thumbnail);
    this.regAt = thumbnail.getRegAt();
    this.modAt = thumbnail.getModAt();
  }

}
