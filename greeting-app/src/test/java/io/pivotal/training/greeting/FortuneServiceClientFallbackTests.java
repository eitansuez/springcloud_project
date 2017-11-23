package io.pivotal.training.greeting;

import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;
import org.springframework.web.client.RestTemplate;

import java.util.Map;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.any;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = NONE)
public class FortuneServiceClientFallbackTests {
  @MockBean RestTemplate restTemplate;
  @Autowired private FortuneServiceClient fortuneServiceClient;

  @Before
  public void setup() {
    when(restTemplate.getForObject(anyString(), any()))
        .thenThrow(new RuntimeException("something went wrong"));
  }

  @Test
  public void shouldFallbackToDefaultFortune() {
    String defaultFortune = fortuneServiceClient.defaultFortune();

    String fortune = fortuneServiceClient.getFortune();

    assertThat(fortune).isEqualTo(defaultFortune);
  }
}

