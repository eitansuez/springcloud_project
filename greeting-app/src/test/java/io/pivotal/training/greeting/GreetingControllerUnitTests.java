package io.pivotal.training.greeting;

import io.pivotal.training.fortune.FortuneService;
import org.junit.Before;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.mockito.runners.MockitoJUnitRunner;
import org.springframework.ui.ExtendedModelMap;

import static org.assertj.core.api.Assertions.assertThat;
import static org.mockito.Mockito.never;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

@RunWith(MockitoJUnitRunner.class)
public class GreetingControllerUnitTests {

  @Mock private GreetingProperties greetingProperties;
  @Mock private FortuneService fortuneService;
  private GreetingController controller;
  private ExtendedModelMap model;

  @Before
  public void setup() {
    MockitoAnnotations.initMocks(GreetingControllerUnitTests.class);
    controller = new GreetingController(greetingProperties, fortuneService);
    model = new ExtendedModelMap();
  }

  @Test
  public void getGreetingShouldSetMessageModelAttribute() {
    //when..
    controller.getGreeting(model);

    //then..
    assertThat(model.asMap().get("msg")).isEqualTo("Greetings!!!");
  }

  @Test
  public void getGreetingShouldNotSetFortuneAttribute() {
    //given..
    when(greetingProperties.isDisplayFortune()).thenReturn(false);

    //when..
    controller.getGreeting(model);

    //then..
    verify(fortuneService, never()).getFortune();
    assertThat(model.asMap().get("fortune")).isNull();
  }

  @Test
  public void getGreetingShouldSetFortuneAttributeWhenConfigured() {
    //given..
    when(greetingProperties.isDisplayFortune()).thenReturn(true);
    when(fortuneService.getFortune()).thenReturn("a fortune");

    //when..
    controller.getGreeting(model);

    //then..
    verify(fortuneService).getFortune();
    assertThat(model.asMap().get("fortune")).isEqualTo("a fortune");
  }

}
