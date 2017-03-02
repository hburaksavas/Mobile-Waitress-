
var temp = null;

function showform(id) {

    if (temp !== null)
        document.getElementById(temp).style.display = 'none';

    document.getElementById(id).style.display = 'inline';
    temp = id;
}

function hideform(id) {

    document.getElementById(id).style.display = 'none';

}

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

