package com.joun.sosmall.test;

import org.apache.ibatis.annotations.Mapper;

import java.util.Map;

@Mapper
public interface TestMapper {
  void add(Map<String, Object> param);
}
