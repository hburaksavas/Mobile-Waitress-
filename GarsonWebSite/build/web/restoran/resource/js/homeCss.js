
var closeTable = function (id) {
    var elem = document.getElementById(id);
    elem.className = "table-buton-name";
};

var openTable = function (id) {
    var elem = document.getElementById(id);
    elem.className = "table-buton-name-opened";
};

var stepTime = 20;
var docBody = document.body;
var focElem = document.documentElement;

var scrollAnimationStep = function (initPos, stepAmount) {
    var newPos = initPos - stepAmount > 0 ? initPos - stepAmount : 0;

    docBody.scrollTop = focElem.scrollTop = newPos;

    newPos && setTimeout(function () {
        scrollAnimationStep(newPos, stepAmount);
    }, stepTime);
};

var scrollTopAnimated = function (speed) {
    var topOffset = docBody.scrollTop || focElem.scrollTop;
    var stepAmount = topOffset;

    speed && (stepAmount = (topOffset * stepTime) / speed);

    scrollAnimationStep(topOffset, stepAmount);
};


document.onkeydown = function ()
{
    switch (event.keyCode)
    {
        case 116 : //F5 button
            event.returnValue = false;
            event.keyCode = 0;
            return false;
        case 82 : //R button
            if (event.ctrlKey)
            {
                event.returnValue = false;
                event.keyCode = 0;
                return false;
            }
    }
};

var show = function (id)
{
    document.getElementById(id).style.display = "inline";
};