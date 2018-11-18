package be.reactiveprogramming.anomalydetectionnats.sender.controller;

import be.reactiveprogramming.anomalydetectionnats.sender.sender.MeasurementSender;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.ResponseBody;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Flux;

@RestController
public class MeasurementSenderController {

  private final MeasurementSender measurementSender;

  public MeasurementSenderController(MeasurementSender measurementSender) {
    this.measurementSender = measurementSender;
  }

  /**
   * TODO 01 README: This RestController uses Spring WebFlux to offer a "web" part to Project Reactor. When a HTTP call is done to the /sendMeasurements
   * endpoint, it will trigger the sendMeasurement method on the measurementSender. This will return a Flux with in it the N'th address that was
   * sent sequentially. This number will flow on through the Flux to the original caller of this method through a HTTP EventStream
   *
   * When clicking on the "sendMeasurements" button now in the UI you'll get a message "Could not send measurements" because
   * the gateway is not up yet. We'll start it up soon though.
   */

  @GetMapping(value = "/sendMeasurements", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  @ResponseBody
  public Flux<Integer> eventStream() {
    return measurementSender.sendMeasurement();
  }

}