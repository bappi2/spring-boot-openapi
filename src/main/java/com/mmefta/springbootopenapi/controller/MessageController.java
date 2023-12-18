package com.mmefta.springbootopenapi.controller;

import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping("/message")
public class MessageController {
    @GetMapping("{name}")
    ResponseEntity<String> message(@PathVariable String name) {
        return new ResponseEntity<>("hello : " + name, HttpStatus.OK);
    }
}
