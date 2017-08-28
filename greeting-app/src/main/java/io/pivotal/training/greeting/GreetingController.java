package io.pivotal.training.greeting;

import io.pivotal.training.fortune.FortuneService;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.GetMapping;

@Controller
public class GreetingController {

  private final Logger logger = LoggerFactory.getLogger(GreetingController.class);

  private final GreetingProperties greetingProperties;
  private final FortuneService fortuneService;

  public GreetingController(GreetingProperties greetingProperties, FortuneService fortuneService) {
    this.greetingProperties = greetingProperties;
    this.fortuneService = fortuneService;
  }

  @GetMapping("/")
  String getGreeting(Model model) {

    logger.debug("Adding greeting");
    model.addAttribute("msg", "Greetings!!!");

    if (greetingProperties.isDisplayFortune()) {
      logger.debug("Adding fortune");
      model.addAttribute("fortune", fortuneService.getFortune());
    }

    return "greeting"; // resolves to the greeting.ftl template
  }

}
