분산 추적 정보와 상세한 로그 메시지를 함께 수집하고 분석하는 경우, 다음과 같은 도구들이 주로 사용됩니다:

1. **Elastic Stack (ELK Stack)**: Elasticsearch, Logstash, Kibana의 조합인 ELK 스택은 로그 데이터를 중앙에서 수집하고 저장, 검색, 시각화하는 데 유용합니다. Logstash는 다양한 소스에서 로그 데이터를 수집하고 처리하여 Elasticsearch에 저장하며, Kibana는 이 데이터를 시각화합니다. Filebeat와 같은 경량 로그 쉬퍼도 사용할 수 있습니다.

2. **Loki**: Grafana Labs에서 개발한 Loki는 특히 Prometheus 및 Grafana와 함께 사용할 때 잘 동작하는 로깅 백엔드입니다. Loki는 메타데이터 인덱싱 방식을 사용하여 기존의 전문적인 로깅 시스템보다 효율적이며 코스트가 저렴합니다.

3. **Fluentd/Fluent Bit + Elasticsearch + Kibana**: Fluentd 또는 Fluent Bit을 사용하여 서비스에서 발생하는 로그를 수집하고 처리한 후 Elasticsearch에 저장할 수 있습니다. 그런 다음 Kibana로 이러한 데이터를 시각화할 수 있습니다.

4. **Graylog**: Graylog 역시 매우 강력한 오픈소스 로깅 플랫폼으로 Elasticsearch 기반으로 작동합니다.

5. **Splunk**: Splunk는 상용 제품이지만 강력한 검색 및 분석 기능을 제공하여 대규모 환경에서의 로깅에 유용합니다.

6. **Humio**: Humio 역시 실시간 분석을 위해 설계된 상용로그 관리 플랫폼입니다.

위 도구들 중 어느 것이 가장 적합한지 결정하기 위해서는 여러 가지 요소들을 고려해야 합니다: 예산(오픈 소스 vs 상용), 운영 부담(호스팅 vs 관리형 서비스), 필요 기능(검색, 경고 등), 그리고 이미 구축된 인프라와의 호환성 등입니다.

### Jaeger
#### 장점
- 모르겠음

### 단점
- tracing 정보만 처리 가능
- 로그 확인 불가; 다른 로그 중앙화 가능한 서비스와 사용해야 함

### Grafana + Loki + Tempo
#### 장점
- 사용성 좋음: Loki(Log정보) <-> Tempo(Tracing정보)간 링크 기능 제공
- 파일시스템을 Storage로 사용 가능, 특별한 DB필요 없음
- 추가 기능; Metric 모니터링(프로메테우스), 알람 등

#### 단점
- Grafana의 라이센스 이슈

### ElasticSearch + Kibana
#### 장점


#### 단점
- 예상 : Elasticsearch가 원래 로그 저장 및 검색을 목적으로 설계되었다는 점입니다. 따라서 대규모 시스템에서 고성능 실시간 메트릭 모니터링을 필요로 하는 경우 Prometheus와 같은 도구가 더 적합할 수도 있다는 것입니다.
