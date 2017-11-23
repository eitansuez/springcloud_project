package io.pivotal.training.greeting;

import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
public class FortuneServiceClient {
  private RestTemplate restTemplate;

  @Value("${fortuneService.baseUrl}")
  private String baseUrl;

  public FortuneServiceClient(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  public String getFortune() {
    Map<String,String> result = restTemplate.getForObject(baseUrl, Map.class);
    return result.get("fortune");
  }
}
