
package com.jiawa.wiki.controller;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @Value("${test.user:TEST_USER}")
    private String testUser;

    @GetMapping("/hello")
    public String helloTest() {
        return "Hello World: " + testUser;
    }

    @PostMapping("/hello/post")
    public ResponseEntity<String> helloPost(String name) {
        return  new ResponseEntity<>("Hello " + name, HttpStatus.OK);
    }
}