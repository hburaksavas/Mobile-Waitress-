var resid;
var connectGarsonServer = function (rid)
{
    resid = rid;
    websocket = new WebSocket("ws://localhost:8080/mobilgarsonweb/garsonhome");

    websocket.onerror = function (event) {
        onError(event);
    };
    websocket.onopen = function (event) {
        onOpen(event);
    };
    websocket.onmessage = function (event) {
        onMessage(event);
    };
    websocket.onclose = function (event) {
        onClose(event);
    };
};

var sendMessage = function (msg) {
    console.log("sending text: " + msg);
    websocket.send(msg);
};
var onMessage = function (event) {
    console.log("receivng text: " + event.data);
    analyzeMessage(event.data);
};
var onOpen = function (event) {
    var msg = "add:restaurant:" + resid;
    sendMessage(msg);
};
var onError = function (event) {
    console.log("onError: " + event.data);
};
var onClose = function (event) {
    console.log("Closing Connection");
};

var analyzeMessage = function (msg)
{
    if (msg.indexOf("new:order") !== -1)
    {
        orderListInvoke();
    }
    else if (msg.indexOf("request") !== -1)
    {

        var arr;
        if (msg.indexOf("waitress") !== -1)
        {
            arr = msg.split(":");
            invokeWaitress(arr[2]);
        }
        else if (msg.indexOf("bill") !== -1)
        {
            arr = msg.split(":");
            invokeBill(arr[2]);
        }
    }
    else if (msg.indexOf("table") !== -1)
    {
        var arr;
        if (msg.indexOf("open") !== -1)
        {
            arr = msg.split(":");
            invokeTable(arr[2], "open");

        }
        else if (msg.indexOf("close") !== -1)
        {
            arr = msg.split(":");
            invokeTable(arr[2], "close");
        }
    }
};

var invokeTable = function (tableno, statu)
{
    var id = "formTables:T-" + tableno;
    var elem = document.getElementById(id);

    if (statu === "open")
    {
        elem.className = "table-buton-name-opened";
    }
    else
    {
        elem.className = "table-buton-name";
    }
};

var invokeBill = function (tbleno) {

    var id = "formTables:bill-" + tbleno;
    var elem = document.getElementById(id);

    elem.className += " animate-bill";
};

var invokeWaitress = function (tblno)
{
    var id = "formTables:waitress-" + tblno;
    var elem = document.getElementById(id);
    elem.className += " animate-waitress";

};

var removeAnimateClass = function (id) {
    var elem = document.getElementById(id);
    elem.className = "buton-img";
};

function orderListInvoke() {
    var jsfCommandLink = document.getElementById("jsOrderInvoke:link");
    jsfCommandLink.click();
}