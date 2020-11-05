var box, but, messagesOutput, userNameInput, messageInput, socket, ul, scrollUp, icon, musicBox, backwardBut, forwardBut, playBut, label;

box = document.getElementById("chat-box");
but = document.getElementById("but");
messagesOutput = document.getElementById("messages-area");
userNameInput = document.getElementById("username");
messageInput = document.getElementById("message");
ul = document.getElementById("ul");
icon  = document.getElementById("chat-icon");
scrollUp = document.getElementById("scroll-up");
musicBox = document.getElementById("music-box");
backwardBut = document.getElementById("backward-button");
forwardBut = document.getElementById("forward-button");
playBut = document.getElementById("play-button");
label = document.getElementById("lbl");


function activatePlayerButtons() {
backwardBut.style.textShadow = "0px 0px 6px 5px orangee," +
    "0px 0px 6px 5px orangee";
}




function standByOn() {
    icon.style.color = "green";
    icon.style.textShadow =
        "1px 1px 3px black, -1px -1px 3px black";

}

function standByOff() {
    icon.style.color = "red";
    icon.style.textShadow = "1px 1px 3px black, -1px -1px 3px black";

}


function standBy() {
    icon.className = "fa fa-power-off";
    icon.style.color = "red";
    icon.style.textShadow =
        "0px 0px 3px black, 0px 0px 3px black";
}





function connectme() {
    // Connect to the WebSocket server
    socket = new WebSocket("ws://" + window.location.hostname + ":" + window.location.port + "/restweb/chat");

    // Receive WebSocket messages
    socket.onmessage = function (message) {
        messagesOutput.value += message.data + '\r';
    };
    //
    // var cls = document.getElementById("closeButton");
    // cls.style.display = "block";
    standByOn();
}



    function sendMessage() {
        var userName = userNameInput.value == '' ? "[Anonymous] " : '[' + userNameInput.value + '] ';

        socket.send(userName + (messageInput.value));
        messageInput.value = "";
        messageInput.focus();

}

function clickScrollUp() {
    box.style.transform = "translateY(0px)";
    but.style.transform = "translateY(0px)";
    scrollUp.style.transform = "translateY(0px)";
    but.style.boxShadow = "3px 1px 3px #2d2d2d, -2px -2px 3px rgba(90, 90, 90, 0.91)";
    scrollUp.style.boxShadow = "0px 0px 0px rgba(255, 157, 0, 1)";
    ul.style.boxShadow = "#9aba26 1px 1px 3px 3px";

}

var SetTopRed = function () {
    ul.style.boxshadow = " red 2px 2px 5px , red -2px -2px 5px ";
    but.setAttribute("class", "top-red");
    but.setAttribute("onClick", "javascript: Set();");
    but.style.transform = "translateY(0px)";
    scrollUp.style.transform = "translateY(0px)";
    box.style.transform = "translateY(0px)";
    but.style.boxShadow = "0px 0px 3px red, 0px 0px 3px red";
}


var SetTopGreen = function () {
    ul.style.boxshadow = "inset green 2px 2px 5px,inset green 2px 2px 5px";
    but.setAttribute("class", "top-green");
    but.setAttribute("onClick", "javascript: Set();");
    but.style.transform = "translateY(0px)";
    scrollUp.style.transform = "translateY(0px)";
    box.style.transform = "translateY(0px)";
}

var SetBottomGreen = function () {
    but.setAttribute("class", "bottom-green");
    but.style.transform = "translateY(40px)";
    scrollUp.style.transform = "translateY(102px)";
box.style.transform = "translateY(540px)";
    but.setAttribute("onClick", "javascript: Set()");
}


function showMusic() {
    musicBox.style.transform = "translateY(350px)";
   // activatePlayerButtons();



}



function lbl(){


    document.getElementById("lbl").style.display = "block";


}
function hoverBut() {
    but.style.textShadow = " 0px 0px 2px white, 0px 0px 2px white";

    but.style.boxShadow = "inset 1px 1px 3px rgba(9, 9, 9, 0.91),inset -1px -1px 3px rgba(0, 0, 0, 0.91)";

}

function show() {
    standBy();
    but.style.transform = "translateY(40px)";
    scrollUp.style.transform = "translateY(102px)";
    scrollUp.style.color = "white";
    ul.style.boxShadow = "  2px 2px 10px red,  -2px -2px 10px red";

    but.style.boxShadow = " 1px 1px 3px red, -1px -1px 3px red";
    but.addEventListener(onmouseover, hoverBut());

    but.setAttribute("class", "bottom-red");


    but.setAttribute("onClick", "javascript: Set();");
    scrollUp.setAttribute("onClick", "javascript: SetTopRed();")
    box.style.transform = "translateY(540px)";
}

    var Set = function () {
        if(but.getAttribute("class") == "bottom-red"){

            socket = new WebSocket("ws://" + window.location.hostname + ":" + window.location.port + "/restweb/chat");

            standByOn();

            socket.onmessage = function (message) {
                messagesOutput.value += message.data + '\r';


            }
    
                but.setAttribute("class", "bottom-green");
                but.setAttribute("onClick", "javascript: Set();");
                box.style.transform = "translateY(540px)";

                 but.style.boxShadow=" 1px 1px 3px green, -1px -1px 3px green";
                but.style.border = "2px solid green";

                ul.style.boxShadow =  "green 2px 2px 6px";

                scrollUp.setAttribute("onClick", "javascript: SetTopGreen();")
                // scrollUp.setAttribute("onClick", bottomRed());
                 showSendBut() ;
                showMessageInput();

                changeStatusOnline();



        }else if(but.getAttribute("class") == "bottom-green"){
            socket.close();
            standByOff();
         but.setAttribute("class", "bottom-red")
            but.setAttribute("onClick", "javascript: Set();");
         scrollUp.setAttribute("onClick", "javascript: SetTopRed()");
            ul.style.boxShadow =  "red 1px 1px 6px, red 1px 1px 6px";

           but.style.border = "solid 1px red" ;
           but.style.boxShadow =  "1px 1px 3px red, -1px -1px 3px red";

         hideMessageInput();
         hideSendBut();
         changeStatusOffline();

        }else if(but.getAttribute("class") == "top-red"){
            but.setAttribute("class", "bottom-red");
            but.style.transform = "translateY(40px)";
            scrollUp.style.transform = "translateY(102px)";
            but.setAttribute("onClick", "javascript: Set();");
            box.style.transform = "translateY(540px)";

        }else if(but.getAttribute("class") == "top-green"){

            but.setAttribute("onClick", "javascript: Set();" );
            but.setAttribute("class", "bottom-green");
            but.style.transform = "translateY(40px)";
            scrollUp.style.transform = "translateY(102px)";
            box.style.transform = "translateY(540px)";
        }
    }




function bottomRed () {
    but.setAttribute("class", "top-red");
    but.setAttribute("onClick", "javascript: Set()");
    box.style.transform = "translateY(0px)";
    but.style.transform = "translateY(0px)";
    scrollUp.style.transform = "translateY(0px)";
}





function hideSendBut() {
    document.getElementById("sendButton").style.display = 'none';
}

function showSendBut() {
    document.getElementById("sendButton").style.display = 'block';
}

function hideMessageInput(){
    document.getElementById("message").style.display = 'none';
}

function showMessageInput(){
    document.getElementById("message").style.display = 'inline';
}



function changeStatusOnline() {
    document.getElementById("status").textContent = 'online';
    document.getElementById("status").style.color = 'green';
    // var soundLink2 = $("#saber")[0];
    // soundLink2.play();
}

function changeStatusOffline() {
    document.getElementById("status").textContent = 'OFFLINE';
    document.getElementById("status").style.color = 'red';
}