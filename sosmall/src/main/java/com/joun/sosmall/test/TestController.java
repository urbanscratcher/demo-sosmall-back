package com.joun.sosmall.test;

import java.util.HashMap;
import java.util.Map;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/tests")
public class TestController {

  @Autowired
  private TestService testService;

  @GetMapping("")
  public String get() {
    return "test";
  }

  @RequestMapping(value = "", method = RequestMethod.POST)
  public void addTest() throws Exception {
    Map<String, Object> body = new HashMap<>() {
      {
        put("test", "test");
      }
    };
    testService.addTest(body);
  }

}
