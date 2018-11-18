package be.reactiveprogramming.anomalydetectionnats.gateway.controller;

import be.reactiveprogramming.anomalydetectionnats.common.event.MeasurementEvent;
import be.reactiveprogramming.anomalydetectionnats.gateway.gateway.MeasurementGateway;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestMethod;
import org.springframework.web.bind.annotation.RestController;
import reactor.core.publisher.Mono;

@RestController
public class MeasurementGatewayController {

  private MeasurementGateway measurementGateway;

  @Autowired
  public MeasurementGatewayController(MeasurementGateway measurementGateway) {
    this.measurementGateway = measurementGateway;
  }

  /**
   * TODO 04 README: As you can see here, this is the entry point where we'll receive a measurementEvent coming in from the Sender application.
   * Look into what happens in the sendMeasurement method.
   */
  @RequestMapping(method = RequestMethod.POST, path = "measurements")
  public Mono<Void> receiveMeasurement(@RequestBody MeasurementEvent measurementEvent) {
    return measurementGateway.sendMeasurement(measurementEvent);
  }

}
