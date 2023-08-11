package com.example.opentelemetrypoc;

import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {
  @GetMapping("say-hello")
  String sayHello() {
    return "Hello";
  }
}
