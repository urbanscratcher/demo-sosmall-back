package com.joun.sosmall.entity;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;

@Entity
@Getter
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Bank {

  @Id
  @Column(columnDefinition = "char(3)")
  private String code;

  @Column(nullable = false, length = 20)
  private String name;

  @Column(columnDefinition = "text")
  private String logoUrl;

  @Builder
  public Bank(String code, String name, String logoUrl) {
    this.code = code;
    this.name = name;
    this.logoUrl = logoUrl;
  }
}
