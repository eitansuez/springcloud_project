package io.pivotal.training.fortune;

import java.util.Random;

import org.springframework.stereotype.Service;

@Service
public class FortuneService {

  private static final String[] Fortunes = {
      "You learn from your mistakes... You will learn a lot today.",
      "You can always find happiness at work on Friday",
      "You will be hungry again in one hour.",
      "Today will be an awesome day!"
  };

  private Random random = new Random();

  public String getFortune() {
    return Fortunes[randomIndexIntoFortunes()];
  }

  private int randomIndexIntoFortunes() {
    return random.nextInt(Fortunes.length);
  }

}
