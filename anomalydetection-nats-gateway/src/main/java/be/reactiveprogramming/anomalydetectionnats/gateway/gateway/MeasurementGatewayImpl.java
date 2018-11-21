package be.reactiveprogramming.anomalydetectionnats.gateway.gateway;

import be.reactiveprogramming.anomalydetectionnats.common.event.MeasurementEvent;
import be.vanseverk.reactor.natsstreaming.ReactiveNatsStreaming;
import be.vanseverk.reactor.natsstreaming.Sender;
import com.fasterxml.jackson.core.JsonProcessingException;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.streaming.StreamingConnection;
import io.nats.streaming.StreamingConnectionFactory;
import java.io.IOException;
import java.util.Random;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Mono;

@Component
public class MeasurementGatewayImpl implements MeasurementGateway {

  private Sender sender;
  private ObjectMapper objectMapper = new ObjectMapper();

  public MeasurementGatewayImpl() throws IOException, InterruptedException {
    //StreamingConnection sc = new StreamingConnectionFactory("test-cluster", "client-" + new Random().nextInt()).createConnection();
    //sender = ReactiveNatsStreaming.createSender(sc);
  }

  private byte[] toBinary(MeasurementEvent body) {
    try {
      return objectMapper.writeValueAsBytes(body);
    } catch (JsonProcessingException e) {
      throw new IllegalArgumentException(e);
    }
  }

  /**
   * TODO 05 Right now this method just prints out the measurement value, but we want a measurement to be sent to NATS instead.
   * Uncomment the lines in the constructor and use the sender to send the measurement to the "measurements" subject on NATS.
   * This subject will be created for us automatically the moment we first send a message to it. This will add the event to the eventLog of the topic "measurements".
   * The sender will send in a non-blocking way, so the Reactive Stream will continue the moment NATS confirms
   * the message arrival.
   */
  @Override
  public Mono<Void> sendMeasurement(MeasurementEvent measurementEvent) {
    System.out.println("Measurement to send to NATS: " + measurementEvent);
    return Mono.empty();
  }
}
