package io.pivotal.training.greeting;

import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@Slf4j
public class FortuneServiceClient {
  private RestTemplate restTemplate;

  public FortuneServiceClient(RestTemplate restTemplate) {
    this.restTemplate = restTemplate;
  }

  @HystrixCommand(fallbackMethod = "defaultFortune")
  public String getFortune() {
    Map<String,String> result = restTemplate.getForObject("http://fortune/", Map.class);
    String fortune = result.get("fortune");
    log.info("received fortune '{}'", fortune);
    return fortune;
  }

  public String defaultFortune() {
    log.info("Default fortune used.");
    return "Your future is uncertain";
  }

}
