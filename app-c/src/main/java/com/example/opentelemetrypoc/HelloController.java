package com.example.opentelemetrypoc;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@Slf4j
public class HelloController {

  @Value("${spring.application.name:not-set}")
  private String serviceName;

  @GetMapping("say-hello")
  String sayHello1() {
    log.info("{} called", serviceName);
    return "Hello I am " + serviceName;
  }

  @GetMapping("app-c/say-hello")
  String sayHello2() {
    log.info("{} called", serviceName);
    return "Hello I am " + serviceName;
  }
}
