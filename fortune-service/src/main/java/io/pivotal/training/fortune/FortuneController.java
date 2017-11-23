package io.pivotal.training.fortune;

import lombok.extern.slf4j.Slf4j;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class FortuneController {

  private FortuneService fortuneService;

  public FortuneController(FortuneService fortuneService) {
    this.fortuneService = fortuneService;
  }

  @GetMapping("/")
  public Map<String, String> getFortune() {
    String fortune = fortuneService.getFortune();
    log.info("retrieving fortune '{}'", fortune);

    Map<String, String> map = new HashMap<>();
    map.put("fortune", fortune);
    return map;
  }

}
