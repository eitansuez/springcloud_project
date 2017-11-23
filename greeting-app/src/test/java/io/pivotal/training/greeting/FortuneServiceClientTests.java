package io.pivotal.training.greeting;

import com.github.tomakehurst.wiremock.client.WireMock;
import com.github.tomakehurst.wiremock.junit.WireMockRule;
import org.junit.Before;
import org.junit.Rule;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.test.context.junit4.SpringRunner;

import static com.github.tomakehurst.wiremock.client.WireMock.aResponse;
import static com.github.tomakehurst.wiremock.client.WireMock.stubFor;
import static com.github.tomakehurst.wiremock.client.WireMock.urlEqualTo;
import static com.github.tomakehurst.wiremock.core.WireMockConfiguration.wireMockConfig;
import static org.assertj.core.api.Assertions.assertThat;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = NONE)
public class FortuneServiceClientTests {

  @Autowired private FortuneServiceClient fortuneServiceClient;

  @Rule
  public WireMockRule wireMockRule = new WireMockRule(wireMockConfig().port(8081));

  private static final String ExpectedFortune = "some fortune";

  @Before
  public void setup() {
    stubFor(WireMock.get(urlEqualTo("/"))
        .willReturn(aResponse()
            .withStatus(200)
            .withHeader("Content-Type", "application/json")
            .withBody(String.format("{ \"fortune\": \"%s\" }", ExpectedFortune))
        ));
  }

  @Test
  public void shouldReturnAFortune() {
    assertThat(fortuneServiceClient.getFortune()).isEqualTo(ExpectedFortune);
  }

}
