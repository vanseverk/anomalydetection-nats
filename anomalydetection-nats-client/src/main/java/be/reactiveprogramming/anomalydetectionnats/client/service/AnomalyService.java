package be.reactiveprogramming.anomalydetectionnats.client.service;

import be.reactiveprogramming.anomalydetectionnats.common.event.AnomalyEvent;
import java.util.Optional;
import reactor.core.publisher.Flux;

public interface AnomalyService {

  Flux<AnomalyEvent> streamAnomalies(Optional<String> deviceId);

}
