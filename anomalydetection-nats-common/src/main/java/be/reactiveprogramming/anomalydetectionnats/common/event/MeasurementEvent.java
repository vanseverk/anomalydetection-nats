package be.reactiveprogramming.anomalydetectionnats.common.event;

import java.math.BigDecimal;
import java.time.LocalDateTime;

public class MeasurementEvent {

  private String timestamp;
  private String deviceId;
  private BigDecimal measurementValue;

  public MeasurementEvent() {
  }

  public MeasurementEvent(String timestamp, String deviceId, BigDecimal measurementValue) {
    this.timestamp = timestamp;
    this.deviceId = deviceId;
    this.measurementValue = measurementValue;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public BigDecimal getMeasurementValue() {
    return measurementValue;
  }
}
