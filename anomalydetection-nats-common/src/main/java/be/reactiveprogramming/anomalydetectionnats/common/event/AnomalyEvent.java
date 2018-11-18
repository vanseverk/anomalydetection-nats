package be.reactiveprogramming.anomalydetectionnats.common.event;

public class AnomalyEvent {

  private String timestamp;
  private String deviceId;
  private String anomaly;

  public AnomalyEvent() {
  }

  public AnomalyEvent(String timestamp, String deviceId, String anomaly) {
    this.timestamp = timestamp;
    this.deviceId = deviceId;
    this.anomaly = anomaly;
  }

  public String getTimestamp() {
    return timestamp;
  }

  public String getDeviceId() {
    return deviceId;
  }

  public String getAnomaly() {
    return anomaly;
  }
}
