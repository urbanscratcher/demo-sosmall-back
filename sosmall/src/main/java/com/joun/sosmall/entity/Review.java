package com.joun.sosmall.entity;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.FetchType;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.OneToMany;

import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.joun.sosmall.common.BaseTimeEntity;
import com.joun.sosmall.dtoRequest.ReviewCreateDto;
import com.joun.sosmall.member.Member;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Where(clause = "del_at is null")
@SQLDelete(sql = "UPDATE review SET del_at = NOW() WHERE id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
public class Review extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "member_id", nullable = false)
  private Member member;

  @ManyToOne(fetch = FetchType.LAZY, optional = false)
  @JoinColumn(name = "product_id", nullable = false)
  private Product product;

  @Column(nullable = false, unique = true)
  private int orderedItemId;

  @Column
  private int star;

  @Column(nullable = false, length = 200)
  private String title;

  @Column(nullable = false, columnDefinition = "text")
  private String content;

  @Setter
  @OneToMany(targetEntity = ReviewPhoto.class, mappedBy = "review", cascade = { CascadeType.PERSIST,
      CascadeType.REMOVE })
  private List<ReviewPhoto> photos;

  @Builder
  public Review(int id, Member member, Product product, int orderedItemId, int star, String title, String content,
      List<ReviewPhoto> photos) {
    this.id = id;
    this.member = member;
    this.product = product;
    this.orderedItemId = orderedItemId;
    this.star = star;
    this.title = title;
    this.content = content;
    this.photos = photos;
  }

  public void setUpdate(ReviewCreateDto dto) {
    this.star = dto.getStar();
    this.title = dto.getTitle();
    this.content = dto.getContent();
  }

}
