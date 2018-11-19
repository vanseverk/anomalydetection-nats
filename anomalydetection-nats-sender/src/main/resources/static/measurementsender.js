var startSendingButton = document.getElementById('startSending');
Rx.DOM.click(startSendingButton)
    .subscribe(startSending);

var subscription;
var numberSent = 0;

function startSending() {
    if (subscription != null) {
        subscription.dispose();
    }
    document.getElementById("message").innerHTML = "";

    var eventSource = Rx.DOM.fromEventSource('/sendMeasurements');

    eventSource.doOnError();

    subscription = eventSource
        .subscribe(
            function (n) { return addToLog()},
            function(e) {}
        );
}

function addToLog() {
    numberSent++;
    document.getElementById("message").innerHTML = "Total number sent: " + numberSent;
}
