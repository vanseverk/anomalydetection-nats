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

    var xhr = new XMLHttpRequest();
    xhr.open("POST", "http://localhost:8082/notify/"+document.getElementById("gatewayIp").value+"/amount/"+document.getElementById("numberOfMessages").value, true);
    xhr.setRequestHeader('Content-Type', 'application/json');
    xhr.send();
}

