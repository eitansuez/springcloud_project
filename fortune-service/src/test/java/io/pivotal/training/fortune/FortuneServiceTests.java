package io.pivotal.training.fortune;

import org.junit.Test;

import static org.assertj.core.api.Assertions.assertThat;

public class FortuneServiceTests {

  @Test
  public void shouldReturnANonBlankFortune() {
    FortuneService fortuneService = new FortuneService();
    assertThat(fortuneService.getFortune()).isNotBlank();
  }

}
