var startSendingButton = document.getElementById('startSending');
Rx.DOM.click(startSendingButton)
    .subscribe(startSending);

var subscription;
var numberSent = 0;

document.getElementById("startSending").hidden=true;
document.getElementById("errorMessage").innerHTML="Fill in your IP";

function startSending() {
    if (subscription != null) {
        subscription.dispose();
    }
    document.getElementById("message").innerHTML = "";

        var eventSource = Rx.DOM.fromEventSource("/sendMeasurements/"+document.getElementById("gatewayIp").value+"/amount/"+document.getElementById("numberOfMessages").value);

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

function changeNumberOfMessages(){
    document.getElementById("startSending").hidden=false;
    document.getElementById("errorMessage").innerHTML="";

    if((document.getElementById("numberOfMessages").value<=0)||(document.getElementById("numberOfMessages").value>50000)){
        document.getElementById("startSending").hidden=true;
        document.getElementById("errorMessage").innerHTML="Pick a number between 0 and 50000 and fill in your IP";
    }
    if(document.getElementById("gatewayIp").value==""){
        document.getElementById("startSending").hidden=true;
        document.getElementById("errorMessage").innerHTML="Fill in your IP";
    }
    if(ValidateIPaddress(document.getElementById("gatewayIp").value)){
        document.getElementById("startSending").hidden=true;
        document.getElementById("errorMessage").innerHTML="invalid IP";
    }
}

function ValidateIPaddress(ipaddress) {
  if (/^(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)\.(25[0-5]|2[0-4][0-9]|[01]?[0-9][0-9]?)$/.test(ipaddress)) {
    return (false)
  }
  return (true)
}