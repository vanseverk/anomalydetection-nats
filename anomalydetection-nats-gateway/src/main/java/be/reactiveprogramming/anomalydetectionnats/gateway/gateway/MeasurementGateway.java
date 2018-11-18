package be.reactiveprogramming.anomalydetectionnats.gateway.gateway;

import be.reactiveprogramming.anomalydetectionnats.common.event.MeasurementEvent;
import reactor.core.publisher.Mono;

public interface MeasurementGateway {
  Mono<Void> sendMeasurement(MeasurementEvent measurementEvent);
}
