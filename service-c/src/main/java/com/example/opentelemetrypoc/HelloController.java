package com.example.opentelemetrypoc;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
public class HelloController {

  @Value("${spring.application.name:not-set}")
  private String serviceName;

  @GetMapping("say-hello")
  String sayHello() {
    return "Hello I am " + serviceName;
  }
}
