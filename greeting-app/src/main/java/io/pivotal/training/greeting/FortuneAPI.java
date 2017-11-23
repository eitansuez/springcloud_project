package io.pivotal.training.greeting;

import org.springframework.cloud.netflix.feign.FeignClient;
import org.springframework.web.bind.annotation.RequestMapping;

import java.util.Map;

@FeignClient("fortune")
public interface FortuneAPI {

  @RequestMapping("/")
  Map<String, String> getFortune();

}
