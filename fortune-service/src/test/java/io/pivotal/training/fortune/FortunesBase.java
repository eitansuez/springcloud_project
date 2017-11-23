package io.pivotal.training.fortune;

import com.jayway.restassured.module.mockmvc.RestAssuredMockMvc;
import org.junit.Before;
import org.junit.runner.RunWith;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.boot.test.context.SpringBootTest;
import org.springframework.boot.test.mock.mockito.MockBean;
import org.springframework.test.context.junit4.SpringRunner;

import static org.mockito.Mockito.when;
import static org.springframework.boot.test.context.SpringBootTest.WebEnvironment.MOCK;

@RunWith(SpringRunner.class)
@SpringBootTest(webEnvironment = MOCK)
public abstract class FortunesBase {

  @MockBean private FortuneService fortuneService;
  @Autowired private FortuneController fortuneController;

  @Before
  public void setup() {
    when(fortuneService.getFortune()).thenReturn("a random fortune");

    RestAssuredMockMvc.standaloneSetup(fortuneController);
  }
}
