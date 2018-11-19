package be.reactiveprogramming.anomalydetectionnats.sender.sender;

import be.reactiveprogramming.anomalydetectionnats.common.event.MeasurementEvent;
import java.math.BigDecimal;
import java.time.LocalDateTime;
import java.time.format.DateTimeFormatter;
import java.util.Random;
import org.springframework.http.HttpMethod;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.BodyInserters;
import org.springframework.web.reactive.function.client.WebClient;
import reactor.core.publisher.Flux;
import reactor.core.publisher.Mono;

@Service
public class MeasurementSender {

  private final WebClient wc = WebClient.create("http://localhost:8081");

  private final Random r = new Random();

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
  public Flux<Integer> sendMeasurement() {
    return Flux.range(1, 10000).flatMap(number -> sendMeasurementForNumber(number));
  }

  private Mono<Integer> sendMeasurementForNumber(int number) {
    final MeasurementEvent measurement = new MeasurementEvent(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME), "" + r.nextInt(10), BigDecimal.valueOf(r.nextDouble() * 100));

    return wc.method(HttpMethod.POST).uri("measurements")
        .body(BodyInserters.fromPublisher(Mono.just(measurement), MeasurementEvent.class))
        .exchange().map(r -> number);
  }
}

