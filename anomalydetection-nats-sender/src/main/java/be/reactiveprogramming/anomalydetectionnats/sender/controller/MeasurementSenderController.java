package be.reactiveprogramming.anomalydetectionnats.sender.controller;

import be.reactiveprogramming.anomalydetectionnats.sender.sender.MeasurementSender;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.*;
import reactor.core.publisher.Flux;

@RestController
public class MeasurementSenderController {

  private final MeasurementSender measurementSender;

  public MeasurementSenderController(MeasurementSender measurementSender) {
    this.measurementSender = measurementSender;
  }

  /**
   * TODO 01 README: We'll be using the MeasurementSender application to imitate a large amount of IoT devices. It will be controlled
   * through a webpage that sends this MeasurementController an HTTP call.
   *
   * This RestController uses Spring WebFlux to offer a "web" part to Project Reactor. When a HTTP call is done to the /sendMeasurements
   * endpoint, it will trigger the sendMeasurement method on the measurementSender. This will return a Flux with in it the number of the message that was sent.
   * This number will flow on through the Flux to the client's browser through a HTTP EventStream/SSE.
   *
   * When clicking on the "sendMeasurements" button in the UI nothing will happen yet because the gateway application isn't started up yet.
   */

  @GetMapping(value = "/sendMeasurements/{dest}/amount/{amount}", produces = MediaType.TEXT_EVENT_STREAM_VALUE)
  @ResponseBody
  public Flux<Integer> eventStream(@PathVariable("amount") int amount, @PathVariable("dest") String destination) {
    return measurementSender.sendMeasurement(destination, amount);
  }
}