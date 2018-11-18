package be.reactiveprogramming.anomalydetectionnats.sender;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * TODO 00 README: This is the starting point for any Spring Boot application. When starting this class as a Java application, it will start up
 * all the components within. You can start this application and open your browser to http://localhost:8082
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
public class AnomalySenderApp {

  public static void main(String[] args) {
    SpringApplication.run(AnomalySenderApp.class, args);
  }

}