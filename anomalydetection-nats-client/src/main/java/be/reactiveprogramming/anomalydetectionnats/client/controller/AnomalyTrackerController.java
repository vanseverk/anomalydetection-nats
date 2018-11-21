package be.reactiveprogramming.anomalydetectionnats.client.controller;

import be.reactiveprogramming.anomalydetectionnats.client.service.AnomalyService;
import be.reactiveprogramming.anomalydetectionnats.common.event.AnomalyEvent;
import java.util.Optional;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class AnomalyTrackerController {

  private final AnomalyService anomalyService;

  public AnomalyTrackerController(AnomalyService anomalyService) {
    this.anomalyService = anomalyService;
  }

  /**
   * TODO 06 README: We'll be using this anomalyTrackerController so users can get an EventStream/SSE of anomalies. You can try to run this application
   * and go to http://localhost:8085 to see some "fake" anomalies running in.
   *
   * In case you're interested in some workings of Spring Webflux, be sure to take a look at https://www.reactiveprogramming.be/spring-webflux-servlet-api/
   */
  @GetMapping(value = "/anomalytracker/anomalies", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  @ResponseBody
  public Flux<AnomalyEvent> eventStream() {
    return anomalyService.streamAnomalies(Optional.empty());
  }

  @GetMapping(value = "/anomalytracker/{deviceId}/anomalies", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  @ResponseBody
  public Flux<AnomalyEvent> eventStream(@PathVariable("deviceId") String deviceId) {
    return anomalyService.streamAnomalies(Optional.of(deviceId));
  }

}