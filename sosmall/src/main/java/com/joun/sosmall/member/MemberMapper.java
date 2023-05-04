package com.joun.sosmall.member;

import java.util.List;
import java.util.Map;

import org.apache.ibatis.annotations.Mapper;
import org.apache.ibatis.annotations.Param;

import com.joun.sosmall.dtoRequest.PageSortDto;

@Mapper
public interface MemberMapper {

  List<Member> finds(@Param("page") PageSortDto page, @Param("search") Map<String, Object> search);

  int findsCount(@Param("search") Map<String, Object> search);
}
