package be.reactiveprogramming.anomalydetectionnats.client;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * TODO 10 Congratulations! You've succesfully made this system into an Reactive Event-Streamed based one. If you still have time you can of course
 * start implementing extra ideas you might have. Otherwise see your hosts of this evening to give you more suggestions about what you could add.
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
public class AnomalyClientApp {

  public static void main(String[] args) {
    SpringApplication.run(AnomalyClientApp.class, args);
  }

}
