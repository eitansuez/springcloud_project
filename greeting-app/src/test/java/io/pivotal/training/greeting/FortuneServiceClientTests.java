package io.pivotal.training.greeting;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = NONE)
@AutoConfigureStubRunner(workOffline = true, ids = "io.pivotal.training.springcloud:fortune-service:+:stubs")
public class FortuneServiceClientTests {

  @Autowired private FortuneServiceClient fortuneServiceClient;

  private static final String ExpectedFortune = "a random fortune";

  @Test
  public void shouldReturnAFortune() {
    assertThat(fortuneServiceClient.getFortune()).isEqualTo(ExpectedFortune);
  }

}
