package io.pivotal.training.greeting;

import com.netflix.appinfo.InstanceInfo;
import com.netflix.discovery.EurekaClient;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.cloud.contract.stubrunner.spring.AutoConfigureStubRunner;
import org.springframework.test.context.junit4.SpringRunner;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Matchers.anyBoolean;
import static org.mockito.Matchers.anyString;
import static org.mockito.Mockito.when;
import static org.mockito.MockitoAnnotations.initMocks;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.NONE;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = NONE)
@AutoConfigureStubRunner(workOffline = true, ids = "io.pivotal.training.springcloud:fortune-service:+:stubs:8081")
public class FortuneServiceClientTests {

  @Autowired private FortuneServiceClient fortuneServiceClient;

  @MockBean EurekaClient eurekaClient;
  @Mock InstanceInfo instanceInfo;

  private static final String ExpectedFortune = "a random fortune";

  @Before
  public void setup() {
    initMocks(FortuneServiceClientTests.class);
    when(instanceInfo.getHomePageUrl()).thenReturn("http://localhost:8081/");
    when(eurekaClient.getNextServerFromEureka(anyString(), anyBoolean())).thenReturn(instanceInfo);
  }

  @Test
  public void shouldReturnAFortune() {
    assertThat(fortuneServiceClient.getFortune()).isEqualTo(ExpectedFortune);
  }

}
