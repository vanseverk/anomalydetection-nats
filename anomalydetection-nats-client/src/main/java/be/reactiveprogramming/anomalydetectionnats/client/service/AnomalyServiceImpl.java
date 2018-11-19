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

  private final AnomalyReceiver anomalyReceiver;

  /**
   * TODO 08 Right now we're getting a Stream of events running in, but we want to share the same event with other users of the Client application.
   * Instead of creating a new Flux for every user, simply create a single Flux from the anomalyReceiver that you then turn into a "Hot Stream" using the share command.
   * When this shared Flux exists, you can simply create a new Flux for every user that requires one through the regular method by using the Flux.from() command.
   *
   * For inspiration, be sure to take a look on: https://www.reactiveprogramming.be/project-reactor-flux-sharing/
   */

   //private final Flux<AnomalyEvent> anomalyFlux;

  /**
   * TODO 09 Every user of the client application will now see the same anomalies Streaming in. However, new users won't see the older anomalies Stream in because they're already "gone".
   * Let's try to keep an internal in our anomalyFlux so new Fluxes connecting to it will also receive some of its historical information. You can find more information on how to do so in
   * https://www.reactiveprogramming.be/project-reactor-flux-caching/
   */

  /**
   * TODO 11 An important concept in Reactive Programming is that of Backpressure. It will help a Reactive Stream to decide what to do with new information when dealing with a slow consumer.
   * The processing in our application will go fast, but let's make it slower by adding a sleep of 10ms in the "isAnomaly" method.
   * Because we're dealing with a constant flow of new measurements and we're only interested in the latest ones, let's apply a backpressure strategy to the Flux.
   * We'll keep up to 10000 MeasurementEvents stored up in case of backpressure, and additional ones should be dropped in a "oldest first" fashion.
   *
   * A great article on the subject of backpressure can be found on: https://www.reactiveprogramming.be/project-reactor-backpressure/
   */

  public AnomalyServiceImpl(AnomalyReceiver anomalyReceiver) {
    this.anomalyReceiver = anomalyReceiver;
  }

  /**
   * TODO 07 For our application we're interested in detecting anomalies in measurements. When the measurement value is lower than 2 we want to send a new AnomalyEvent with the text
   * "Value too low {measurement value}" to the user. The deviceId and timestamp can just be copied from the measurementevent it originated from.
   *
   * These MeasurementEvents will be streamed from NATS. We'll be using an "auto acknowledge" method, because we just want to display the information and don't need to do any important
   * processing on the data that might need acknowledgements of the messages that arrived. Working with manual acknowledgements after processing is done is definitely possible, but out
   * of the scope of this exercise.
   *
   * Now let's move to the next implementation step, showing the users some actual anomalies. We'll use the AnomalyReceiver to get a stream of measurements from the NATS server.
   * Use the "isAnomaly" method to filter out the measurements which are in fact anomalies. Afterwards, you can use the map method on the Flux to map these measurementEvents to anomalyEvents.
   *
   * TODO 10 If you haven't yet, add an extra filter on the deviceId. This way a user will only get information regarding the device he wants information of.
   */
  public Flux<AnomalyEvent> streamAnomalies(Optional<String> deviceId) {
    return Flux.interval(Duration.ofSeconds(1)).map(n -> new AnomalyEvent("timestamp", "deviceid", "anomaly"));
  }

  private boolean isAnomaly(MeasurementEvent measurementEvent) {
    return measurementEvent.getMeasurementValue().compareTo(BigDecimal.valueOf(2)) < 1;
  }

}
