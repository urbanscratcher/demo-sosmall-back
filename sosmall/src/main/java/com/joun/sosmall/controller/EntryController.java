package com.joun.sosmall.controller;

import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class EntryController {

    @RequestMapping("/robots.txt")
    public String robots() {
        return "User-agent: *\nDisallow: /\n";
    }
}
