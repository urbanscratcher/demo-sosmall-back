package com.joun.sosmall.test;

import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

@Service
public class TestService {

  @Autowired
  private TestMapper testMapper;
  
  public void addTest(Map<String, Object> param) throws Exception {
    testMapper.add(param);
  }
  

}
