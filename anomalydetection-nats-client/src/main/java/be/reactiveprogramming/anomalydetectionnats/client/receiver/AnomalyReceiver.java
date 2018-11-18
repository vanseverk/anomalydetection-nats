package be.reactiveprogramming.anomalydetectionnats.client.receiver;

import be.reactiveprogramming.anomalydetectionnats.common.event.MeasurementEvent;
import reactor.core.publisher.Flux;

public interface AnomalyReceiver {

  Flux<MeasurementEvent> streamMeasurements();

}
