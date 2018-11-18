package be.reactiveprogramming.anomalydetectionnats.client.receiver;

import be.reactiveprogramming.anomalydetectionnats.common.event.MeasurementEvent;
import be.vanseverk.reactor.natsstreaming.NatsMessage;
import be.vanseverk.reactor.natsstreaming.ReactiveNatsStreaming;
import be.vanseverk.reactor.natsstreaming.Receiver;
import com.fasterxml.jackson.databind.ObjectMapper;
import io.nats.streaming.Message;
import io.nats.streaming.StreamingConnection;
import io.nats.streaming.StreamingConnectionFactory;
import java.io.IOException;
import java.util.Random;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.stereotype.Component;
import reactor.core.publisher.Flux;

@Component
public class AnomalyReceiverImpl implements AnomalyReceiver {

  private Receiver receiver;
  private ObjectMapper objectMapper = new ObjectMapper();

  private static final Logger LOGGER = LoggerFactory.getLogger(AnomalyReceiverImpl.class);

  public AnomalyReceiverImpl() throws IOException, InterruptedException {
    StreamingConnection sc = new StreamingConnectionFactory("test-cluster", "client-" + new Random().nextInt()).createConnection();
    receiver = ReactiveNatsStreaming.createReceiver(sc);
  }

  @Override
  public Flux<MeasurementEvent> streamMeasurements() {
    return receiver.receive("measurements")
        .map(m -> fromBinary(m));
  }

  private MeasurementEvent fromBinary(NatsMessage m) {
    try {
      return objectMapper.readValue(m.getData(), MeasurementEvent.class);
    } catch (Exception e) {
      throw new IllegalArgumentException(e);
    }
  }
}
