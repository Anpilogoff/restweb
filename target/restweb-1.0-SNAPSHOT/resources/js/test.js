    var box, but, messagesOutput, userNameInput, messageInput, socket, ul, scrollUp, icon, musicBox;

box = document.getElementById("chat-box");
but = document.getElementById("but");
messagesOutput = document.getElementById("messages-area");
userNameInput = document.getElementById("username");
messageInput = document.getElementById("message");
ul = document.getElementById("ul");
icon  = document.getElementById("chat-icon");
scrollUp = document.getElementById("scroll-up");
musicBox = document.getElementById("music-box");

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

function clickScrollUp() {
box.style.transform = "translateY(0px)";
but.style.transform = "translateY(0px)";
scrollUp.style.transform = "translateY(0px)";
but.style.boxShadow = "3px 1px 3px #2d2d2d, -2px -2px 3px rgba(90, 90, 90, 0.91)";
scrollUp.style.boxShadow = "0px 0px 0px rgba(255, 157, 0, 1)";
ul.style.boxShadow = "#9aba26 1px 1px 3px 3px";

}


var Set = function() {
    if (but.getAttribute("class") == "bottom-red") { //из show

        socket = new WebSocket("ws://" + window.location.hostname + ":" + window.location.port + "/restweb/chat");
        socket.onmessage = function (message) {
            messagesOutput.value += message.data + '\r';
        };

        // Receive WebSocket messages

        standByOn();
        but.setAttribute("class","bottom-green");
        scrollUp.setAttribute("onClick", "javascript: Set3();")
        but.setAttribute("onClick", "javascript: Set();" );
        showSendBut();
        showMessageInput();
        changeStatusOnline();
        but.style.boxShadow = "0px 0px 8px green";

    } else if (but.getAttribute("class") == "top-red") {
        scrollUp.style.transform = "translateY(95px)";
        scrollUp.setAttribute("onClick", scrollUp)
        scrollUp.style.boxShadow = "0px 0px 8px rgba(255, 157, 0, 1)";
        ul.style.boxShadow = " rgba(255, 157, 0, 1) 1px 1px 3px 3px";
        box.style.transform = "translateY(540px)";
        but.style.transform = "translateY(40px)";
        but.style.boxShadow = "0px 0px 8px rgba(255, 157, 0, 1)";
        but.setAttribute("class", "bottom-red");
        but.setAttribute("onClick", "javascript: Set();");
    }else if(but.getAttribute("class") == "bottom-green"){

        socket.close();
        standByOff();
        but.setAttribute("class", "bottom-red");
        but.style.textShadow = "1px 1px 3px black, -1px -1px 3px black";
        but.setAttribute("onClick", "Set();");
        hideSendBut(),hideMessageInput(), changeStatusOffline()
        scrollUp.setAttribute("onClick", "javascript: Set2();")
    }else if(but.getAttribute("class")== "top-green"){
        but.setAttribute("onClick", "javascript: Set4();");
    }
}



var Set2 = function () {

    but.setAttribute("class", "top-red");


    but.setAttribute("onClick", "javascript: Set();");
clickScrollUp();


    }

var Set3 = function () {


    but.setAttribute("class", "top-green");
    but.style.textShadow = "1px 1px 3px black, -1px -1px 3px black";
    but.setAttribute("onClick", "javascript: Set();");

    clickScrollUp();
    // ul.style.boxShadow = " #9aba26 1px 1px 3px 3px;"
    }

var Set4 = function () {
    scrollUp.style.transform = "translateY(95px)";
    scrollUp.style.boxShadow = "0px 0px 8px rgba(255, 157, 0, 1)";
    ul.style.boxShadow = "rgba(255, 157, 0, 1) 1px 1px 3px 3px";
    box.style.transform = "translateY(540px)";
    but.style.transform = "translateY(40px)";
    but.style.boxShadow = "0px 0px 8px rgba(255, 157, 0, 1)";
    but.style.textShadow = "1px 1px 3px black, -1px -1px 3px black";
    but.setAttribute("class", "bottom-green");
    but.setAttribute("onClick", "javascript: Set();")
    scrollUp.setAttribute("onClick", "javascript: Set3();" );
}


function show() {
    standBy();
    but.style.transform = "translateY(40px)";
    but.style.boxShadow = "  2px 2px 6px rgba(255, 157, 0, 1),  -2px -2px 6px rgba(255, 157, 0, 1)";

    but.setAttribute("class", "bottom-red");
    but.setAttribute("onClick", "javascript: Set();")

    scrollUp.setAttribute("onClick", "javascript: Set2();");

    scrollUp.style.transform = "translateY(102px)";
    scrollUp.style.boxShadow = "  2px 2px 6px rgba(255, 157, 0, 1),  -2px -2px 6px rgba(255, 157, 0, 1)";
    ul.style.boxShadow = " rgba(255, 157, 0, 1) 1px 1px 3px 3px";
    box.style.transform = "translateY(540px)";

}



    function hide() {
but.style.transform = "translateY(0px)";
        box.style.transform = "translateY(0)";
        box.style.transform = "translateX(0px)";
        but.style.boxShadow = "#00c0d7";
    }

function connectme() {
    // Connect to the WebSocket server
    socket = new WebSocket("ws://" + window.location.hostname + ":" + window.location.port + "/restweb/chat");

    // Receive WebSocket messages
    socket.onmessage = function (message) {messagesOutput.value += message.data + '\r';};
    //
    // var cls = document.getElementById("closeButton");
    // cls.style.display = "block";
    standByOn();



}



function sendMessage() {
    var userName = userNameInput.value == '' ? "[Anonymous] " : '[' + userNameInput.value + '] ';
    // создать объект в которм будет
    socket.send(userName + (messageInput.value));
    messageInput.value = "";
    messageInput.focus();
}



function f() {

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

function changeStatusOffline(){
    document.getElementById("status").textContent = 'offline';
    document.getElementById("status").style.color = 'red';


}



function showMusic() {
    musicBox.style.transform = "translateY(500px)";
    musicBox.style.height = "500px";


}


