package be.reactiveprogramming.anomalydetectionnats.client.service;

import be.reactiveprogramming.anomalydetectionnats.client.receiver.AnomalyReceiver;
import be.reactiveprogramming.anomalydetectionnats.common.event.AnomalyEvent;
import be.reactiveprogramming.anomalydetectionnats.common.event.MeasurementEvent;
import java.math.BigDecimal;
import java.time.Duration;
import java.util.Optional;
import org.springframework.stereotype.Service;
import reactor.core.publisher.Flux;

@Service
public class AnomalyServiceImpl implements AnomalyService {

  //private final Flux<AnomalyEvent> anomalyFlux;

  /**
   * TODO 08 Right now we're getting a Stream of events running in, but the moment we've streamed them to the user, we'd have to get the same event
   * again for a second user. Of course we can try to make this more performant by caching and sharing our Flux. Take a look at the following
   * articles and try to apply this here:
   *
   * https://www.reactiveprogramming.be/project-reactor-flux-sharing/
   * https://www.reactiveprogramming.be/project-reactor-flux-caching/
   */
  public AnomalyServiceImpl(AnomalyReceiver anomalyReceiver) {

  }

  /**
   * TODO 07 Now let's move to the next implementation step, showing the users some actual anomalies. The AnomalyReceiver can be used to stream
   * in measurements from the NATS server, which then can be filtered and mapped to AnomalyEvents. The "isAnomaly" method is made slow on purpose to display
   * the concept of backpressure. Even though it's slow, the backpressure will make sure there won't be any more results flowing in through NATS than the application
   * can handle, making sure the memory footprint doesn't get too big.
   *
   * TODO 09 If you haven't yet, add an extra filter on the deviceId. This way a user will only get information regarding the device he wants information of.
   */
  public Flux<AnomalyEvent> streamAnomalies(Optional<String> deviceId) {
    return Flux.interval(Duration.ofSeconds(1)).map(n -> new AnomalyEvent("timestamp", "deviceid", "anomaly"));
  }

  private boolean isAnomaly(MeasurementEvent measurementEvent) {
    try {
      Thread.sleep(10); // Let's simulate some work..
    } catch (InterruptedException e) {
      e.printStackTrace();
    }

    return measurementEvent.getMeasurementValue().compareTo(BigDecimal.valueOf(2)) < 1;
  }

}
