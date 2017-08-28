package io.pivotal.training.greeting;

import org.springframework.boot.context.properties.ConfigurationProperties;
import org.springframework.stereotype.Component;

@Component
@ConfigurationProperties(prefix = "greeting")
public class GreetingProperties {

  private boolean displayFortune;

  public boolean isDisplayFortune() {
    return displayFortune;
  }

  public void setDisplayFortune(boolean displayFortune) {
    this.displayFortune = displayFortune;
  }
}
