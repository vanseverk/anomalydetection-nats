package be.reactiveprogramming.anomalydetectionnats.gateway;

import org.springframework.boot.SpringApplication;
import org.springframework.boot.SpringBootConfiguration;
import org.springframework.boot.autoconfigure.EnableAutoConfiguration;
import org.springframework.context.annotation.ComponentScan;

/**
 * TODO 03 README: Now let's also start up the gateway server, and start sending measurements through the Sender application.
 * Then take a look at your console log. You should see the measurements being received. Take a look at your browser's
 * network tab in the developer console. You'll see the EventStream being returned from the server with the numbers
 * seemingly out of order. This is however the order as the Gateway's replies have been received by the sender.
 */
@SpringBootConfiguration
@EnableAutoConfiguration
@ComponentScan
public class AnomalyGatewayApp {

  public static void main(String[] args) {
    SpringApplication.run(AnomalyGatewayApp.class, args);
  }

}
