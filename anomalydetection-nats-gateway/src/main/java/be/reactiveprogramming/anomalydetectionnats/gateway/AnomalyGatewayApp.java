package be.reactiveprogramming.anomalydetectionnats.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * TODO 03 README: Now let's also start up the gateway server, and start sending measurements through the Sender application.
 * Then take a look at your console log. You should see the measurements being received.
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
public class AnomalyGatewayApp {

  public static void main(String[] args) {
    SpringApplication.run(AnomalyGatewayApp.class, args);
  }

}
