package com.joun.sosmall.entity;

import java.util.ArrayList;
import java.util.Date;
import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Index;
import javax.persistence.OneToMany;
import javax.persistence.Table;

import org.hibernate.annotations.ColumnDefault;
import org.hibernate.annotations.SQLDelete;
import org.hibernate.annotations.Where;

import com.joun.sosmall.common.BaseTimeEntity;
import com.joun.sosmall.dtoRequest.MemberCreateDto;

import lombok.AccessLevel;
import lombok.Builder;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Where(clause = "del_at is null")
@SQLDelete(sql = "UPDATE member SET del_at = NOW() WHERE id = ?")
@NoArgsConstructor(access = AccessLevel.PROTECTED)
@Table(indexes = { @Index(name = "email_idx", columnList = "email") })
public class Member extends BaseTimeEntity {

  @Id
  @GeneratedValue(strategy = GenerationType.IDENTITY)
  @Column
  private int id;

  @Column(length = 100, nullable = false)
  private String name;

  @Column(length = 320, nullable = false, unique = true)
  private String email;

  @Column(length = 50, nullable = false)
  private String password;

  @Column(columnDefinition = "TINYINT(1)", nullable = false)
  @ColumnDefault("0")
  private Boolean isEmailAllowed;

  @Column(length = 20, nullable = false)
  private String phone;

  @Setter
  @Column
  private Date birthAt;

  @OneToMany(targetEntity = Address.class, mappedBy = "member", cascade = { CascadeType.PERSIST, CascadeType.REMOVE })
  private List<Address> addresses = new ArrayList<Address>();

  @Builder
  public Member(int id, String name, String email, String password, Boolean isEmailAllowed, String phone,
      Date birthAt, List<Address> addresses) {
    this.id = id;
    this.name = name;
    this.email = email;
    this.password = password;
    this.isEmailAllowed = isEmailAllowed;
    this.phone = phone;
    this.birthAt = birthAt;
    this.addresses = addresses;
  }

  public void setUpdate(MemberCreateDto dto) {
    if (dto.getName() != null) {
      this.name = dto.getName();
    }

    if (dto.getPassword() != null) {
      this.password = dto.getPassword();
    }

    if (dto.getIsEmailAllowed() != null) {
      this.isEmailAllowed = dto.getIsEmailAllowed();
    }

    if (dto.getPhone() != null) {
      this.phone = dto.getPhone();
    }

    if (dto.getBirthAt() != null) {
      this.birthAt = dto.getBirthAt();
    }

  }

}
