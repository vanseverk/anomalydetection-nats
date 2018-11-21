package be.reactiveprogramming.anomalydetectionnats.sender.sender;

import be.reactiveprogramming.anomalydetectionnats.common.event.MeasurementEvent;

import java.io.OutputStream;
import java.math.BigDecimal;
import java.net.HttpURLConnection;
import java.net.URL;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.List;
import java.util.Random;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MeasurementSender {


  private WebClient wc;

  private final Random r = new Random();

  private String ipList[]=new String[1];
  /**
   * TODO 02 README: This is where the Flux is created which will take care of sending our measurements. A range of numbers from 1 to 10'000 will be generated
   * that will define which number the sent message has. This goes through a flatMap method that calls the sendMeasumentForNumber method along with the number.
   * A random Measurement gets created for a device number between 1 and 10, and this is then sent using the webclient. Because the webclient sends the web request
   * in a non blocking way, we don't have to wait for the result of this call to arrive, so more measurements will get created and sent at the same time.
   * After the webclient receives a response, the reactive pipeline can continue with it. In this case we simply map the result back to the original number
   * however, so we create a Stream of numbers again. These numbers will then run through the controller and streamed to the client's browser. Let's now move
   * to the Gateway application's todos.
   *
   */
  public Flux<Integer> sendMeasurement(String destination, int amount) {
    System.out.println("hier");
    this.wc = WebClient.create("http://"+destination+":8081");
    return Flux.range(1, amount).flatMap(number -> sendMeasurementForNumber(number));
  }

  private Mono<Integer> sendMeasurementForNumber(int number) {

    final MeasurementEvent measurement = new MeasurementEvent(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME), "" + r.nextInt(10), BigDecimal.valueOf(r.nextDouble() * 100));

    return wc.method(HttpMethod.POST).uri("measurements")
            .body(BodyInserters.fromPublisher(Mono.just(measurement), MeasurementEvent.class))
            .exchange().map(r -> number);
  }


}

