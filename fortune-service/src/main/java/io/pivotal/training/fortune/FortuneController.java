package io.pivotal.training.fortune;

import lombok.extern.slf4j.Slf4j;
import org.springframework.beans.factory.annotation.Value;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import java.util.HashMap;
import java.util.Map;

@RestController
@Slf4j
public class FortuneController {

  private FortuneService fortuneService;

  @Value("${delay.ms:0}")
  private int delayMs = 0;

  public FortuneController(FortuneService fortuneService) {
    this.fortuneService = fortuneService;
  }

  @GetMapping("/")
  public Map<String, String> getFortune() {
    String fortune = fortuneService.getFortune();
    log.info("retrieving fortune '{}'", fortune);

    artificialDelay();

    Map<String, String> map = new HashMap<>();
    map.put("fortune", fortune);
    return map;
  }

  private void artificialDelay() {
    if (delayMs <= 0) return;

    try {
      Thread.sleep(delayMs);
    } catch (InterruptedException e) {
      e.printStackTrace();
    }
  }

}
