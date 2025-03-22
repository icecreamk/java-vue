
package com.jiawa.wiki.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class TestController {
    @GetMapping("/hello")
    public String helloTest() {
        return "Hello world";
    }

    @PostMapping("/hello/post")
    public ResponseEntity<String> helloPost(String name) {
        return  new ResponseEntity<>("Hello " + name, HttpStatus.OK);
    }
}