package com.example.opentelemetrypoc;

import java.io.IOException;
import java.util.HashMap;
import java.util.Map;
import lombok.extern.slf4j.Slf4j;
import okhttp3.MediaType;
import okhttp3.OkHttpClient;
import okhttp3.Request;
import okhttp3.RequestBody;
import okhttp3.Response;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PostMapping;
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

  @GetMapping("app-b/say-hello")
  String sayHello2() {
    log.info("{} called", serviceName);
    return "Hello I am " + serviceName;
  }

  public static final MediaType JSON = MediaType.parse("application/json; charset=utf-8");

  @PostMapping({"app-b/call-c-post", "call-c-post"})
  String CallBPost(@org.springframework.web.bind.annotation.RequestBody Map<String, String> body) {
    log.info("{} called", serviceName);
    OkHttpClient client = new OkHttpClient();

    // Request Body 데이터 생성
    Map<String, String> requestBodyData = new HashMap<>();
    requestBodyData.put("key1", "Value1");
    requestBodyData.put("key2", "Value2");

    // Request Body 생성
    RequestBody requestBody = RequestBody.create(convertMapToJson(requestBodyData), JSON);

    Request request = new Request.Builder()
        .url("http://app-c:8080/")
        .post(requestBody)
        .build();

    log.info("{} : I will call app-c", serviceName);
    try (Response response = client.newCall(request).execute()) {
      // 응답 처리 로직 작성
      String responseBody = response.body().string();
      // ...
    } catch (IOException e) {
      // 예외 처리 로직 작성
      e.printStackTrace();
    }
    log.info("{} : I received a response from app-c", serviceName);
    return "I am " + serviceName + ": api works.";
  }

  private static String convertMapToJson(Map<String, String> map) {
    StringBuilder jsonBuilder = new StringBuilder("{");
    for (Map.Entry<String, String> entry : map.entrySet()) {
      jsonBuilder.append("\"").append(entry.getKey()).append("\":\"").append(entry.getValue()).append("\",");
    }
    jsonBuilder.deleteCharAt(jsonBuilder.length() - 1);  // Remove the trailing comma
    jsonBuilder.append("}");
    return jsonBuilder.toString();
  }
}
