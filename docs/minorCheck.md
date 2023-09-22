# Otel SDK 관련
## java code에서 traceId 참조 방법
- Maven depednecy 추가
```xml
    <dependencyManagement>
        <dependencies>
            <dependency>
                <groupId>io.opentelemetry</groupId>
                <artifactId>opentelemetry-bom</artifactId>
                <version>1.30.1</version>
                <type>pom</type>
                <scope>import</scope>
            </dependency>
        </dependencies>
    </dependencyManagement>

    <dependencies>
        <dependency>
            <groupId>io.opentelemetry</groupId>
            <artifactId>opentelemetry-api</artifactId>
        </dependency>
    <dependencies>
```
```java
Span.current().getSpanContext().getTraceId(); // trace id조회
Span.current().getSpanContext().getSpanId(); // span id조회
```
- otel java agent가 비활성화되어 context정보가 없으면 trace정보 조회 코드는 어떻게 동작하는가
```
// Exception나지 않고 000..으로 나옴
[app-b] 2023-09-22 07:24:18,592 WARN  [http-nio-8080-exec-2] com.example.opentelemetrypoc.HelloController: Span.current().getSpanContext().getTraceId():00000000000000000000000000000000
[app-b] 2023-09-22 07:24:18,592 WARN  [http-nio-8080-exec-2] com.example.opentelemetrypoc.HelloController: Span.current().getSpanContext().getSpanId():0000000000000000
```

## logback log남길 때 traceId 참조 방법
- logback 설정에 아래와 같은 formatting
```
trace_id=%mdc{trace_id} span_id=%mdc{span_id} trace_flags=%mdc{trace_flags} %5p
``` 
- https://github.com/open-telemetry/opentelemetry-java-instrumentation/blob/main/docs/logger-mdc-instrumentation.md

## Application -> Otel Collector로 Signal전송 시점
## Otel SDK의 signal전송은 비동기인가
## application log를 Otel Collector로 전송 시 기존 로그파일에 동일하게 남는지
    - otel protocol의 모든 내용이 json format으로 남음
    - 필요한 정보만 남도록 processor 설정 필요

# Otel Collector 관련
## Otel Collector가 로그를 local file로 export하면 경로는?
- 지정 하는 곳으로;hostpath로만 테스트 해봄
## Otel Collector의 Queue Size와 Export불가 시 어떻게 동작하는가?