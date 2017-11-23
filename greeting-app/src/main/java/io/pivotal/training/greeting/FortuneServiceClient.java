package io.pivotal.training.greeting;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import com.netflix.hystrix.contrib.javanica.annotation.HystrixCommand;
import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.stereotype.Component;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

@Component
@Slf4j
public class FortuneServiceClient {
  private RestTemplate restTemplate;
  private EurekaClient eurekaClient;

  public FortuneServiceClient(RestTemplate restTemplate, EurekaClient eurekaClient) {
    this.restTemplate = restTemplate;
    this.eurekaClient = eurekaClient;
  }

  @HystrixCommand(fallbackMethod = "defaultFortune")
  public String getFortune() {
    String baseUrl = lookupUrlFor("FORTUNE");
    Map<String,String> result = restTemplate.getForObject(baseUrl, Map.class);
    String fortune = result.get("fortune");
    log.info("received fortune '{}'", fortune);
    return fortune;
  }

  private String lookupUrlFor(String appName) {
    InstanceInfo instanceInfo = eurekaClient.getNextServerFromEureka(appName, false);
    return instanceInfo.getHomePageUrl();
  }

  public String defaultFortune() {
    log.info("Default fortune used.");
    return "Your future is uncertain";
  }

}
