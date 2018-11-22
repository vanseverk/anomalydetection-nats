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


  private final Random r = new Random();

  /**
   * TODO 02 README: This is where the Flux is created that will take care of sending our measurements. A range of numbers up until the required amount will be generated
   * by our origin Flux. This number will defined which order the sent message originally had. This Stream of numbers will go through a flatMap method that calls
   * the sendMeasumentForNumber method along with the given sequence number. A random Measurement gets created for a device number between 1 and 10,
   * and this is then sent using the webclient. Because the webclient sends the web request in a non blocking way, we don't have to wait for the result of this call to arrive.
   * Instead the Stream will continue for the message after a reply from the Gateway arrives. This means more measurements will get created and sent at the same time.
   * After the message response is received, we simply map the result back to the original number. This means we transform the Stream back to a Stream of numbers.
   * These numbers will then run through the controller and streamed to the client's browser. If you're using the sender later on, be sure to take a look at your
   * browser's developer console to take a look of the numbers running in. Because the stream comes from the server responses, it will likely be out of order.
   *
   * Let's now move to the Gateway application's todos.
   *
   */
  public Flux<Integer> sendMeasurement(String destination, int amount) {
    final WebClient wc = WebClient.create("http://"+destination+":8081");
    return Flux.range(1, amount).flatMap(number -> {

      final MeasurementEvent measurement = new MeasurementEvent(LocalDateTime.now().format(DateTimeFormatter.ISO_DATE_TIME), "" + r.nextInt(10), BigDecimal.valueOf(r.nextDouble() * 100));

      return wc.method(HttpMethod.POST).uri("measurements")
              .body(BodyInserters.fromPublisher(Mono.just(measurement), MeasurementEvent.class))
              .exchange().map(r -> number);

    });
  }

}

