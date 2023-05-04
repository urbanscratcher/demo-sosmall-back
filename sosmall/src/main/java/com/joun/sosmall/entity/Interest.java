package com.joun.sosmall.entity;

import javax.persistence.EmbeddedId;
import javax.persistence.Entity;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Interest {

  @EmbeddedId
  private MemberProductId id;

  @Builder
  public Interest(MemberProductId id) {
    this.id = id;
  }

}
