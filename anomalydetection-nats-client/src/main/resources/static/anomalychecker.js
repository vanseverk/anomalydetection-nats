var deviceIdInput = document.getElementById('deviceId');
Rx.DOM.keyup(deviceIdInput)
    .pluck('target', 'value')
    .debounce(500)
    .subscribe(function (deviceId) { return getAnomalyStream(deviceId); });
var subscription;
function getAnomalyStream(deviceId) {
    var _this = this;
    if (subscription != null) {
        subscription.dispose();
    }
    document.getElementById("trackings").innerHTML = "";
    if (deviceId) {
        subscription = Rx.DOM.fromEventSource('/anomalytracker/' + deviceId + '/anomalies')
            .subscribe(function (anomalyInfo) { return _this.addAnomalyToLog(JSON.parse(anomalyInfo)); });
    }
    else {
        subscription = Rx.DOM.fromEventSource('/anomalytracker/anomalies')
            .subscribe(function (anomalyInfo) { return _this.addAnomalyToLog(JSON.parse(anomalyInfo)); });
    }
}
function addAnomalyToLog(anomalyInfo) {
    var logLine = document.createElement("tr");
    logLine.innerHTML = "<li class='list-group-item'>" + anomalyInfo.timestamp + " " + anomalyInfo.deviceId + " " + anomalyInfo.anomaly + "</li>";
    document.getElementById("trackings").insertBefore(logLine, document.getElementById("trackings").children[0]);
}
this.getAnomalyStream(null);
