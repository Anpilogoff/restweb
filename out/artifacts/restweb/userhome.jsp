<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.JsonElement" %>
<%@ page import="com.anpilogoff.model.entity.User" %>
<html>
<head>
    <style>
        body {position: absolute; width: 100%; height: 100%; margin: 0; background-color: #404345;
        }

        .chat-box {
            position: absolute;
            width: 450px;
            height: 550px;
            box-shadow: #9aba26 1px 1px 3px 3px;
            background-color: #292929;
            top:-300px;
            left: 120px;
            transition: 1s linear;

        }

        .header-container{
            z-index: 2;
            position: absolute;
            width: 100%;
            height: 60px;
            top:1px;
            border-color: black;
            border-width: 5px;
            box-shadow: black 1px 1px 3px 3px;
            background-color: #635454;
        }

        .but{
            text-shadow: -1px -1px #000,
            0 1px 0 #444;
            position: relative;
            font-family: Consolas sans-serif;
            text-decoration: #e6dc19;
            text-shadow: #0c9a11 1px 1px 3px 3px;
            top: 20px;
            float: left;
            margin-left: 30px;
        }

        textarea{
            background-color: darkgreen;
            opacity: 50%;
            margin: 5px;
            box-shadow:inset #cbbfbf 1px 1px 3px 3px;
        }

        #closeButton{
            display: none;
        }


        #connectButton{
            position: relative;
            color: yellow;
            background-color:green ;
        }

        #connectButton:hover{
            color: yellow;
            background-color:green ;
        }

        #sendButton{
            opacity: 75%;
            display: none;
            background-color: darkgrey;
            box-shadow: black 1px 1px 3px 3px;
            border-width: 1px;
            border-color: orangered;

        }
        #sendButton:focus{
            border-width: 2px;

            box-shadow: inset black 2px 2px;
        }
        #sendButton:hover{
            opacity: 100%;
            box-shadow: green 1px 1px 3px 3px;
        }

        #message{
            display: none;
            width: 300px;
            height: 50px;
            font-weight: bold;
        }


        #closeButton {
            box-shadow:  2px 2px 5px rgba(154, 147, 140, 0.5)
        }
        #closeButton:hover {

            box-shadow: inset 2px 2px 5px rgba(154, 147, 140, 0.5), 1px 1px 5px rgba(255, 255, 255, 1);
        }

        #username{
            box-shadow:inset 1px 1px 4px 4px;

        }
        #message{
            color: black;
            opacity: 80%;
            box-shadow: inset black 1px 1px 4px 3px;
        }
        #messages{
            color: #e6dc19;
        }
    </style>
</head>
<body>


<div class="header-container">



    <a href="#" type="button" class="but" id="but3">chat</a>


    <a href="#" type="button" class="but" id="but4">messages</a>

    <a href="#" type="button" class="but" id="but5">contacts</a>
</div>


<div class="chat-box" id="chat-box">
    <textarea style="width: 440px; height: 400px" id="messages"  readonly="readonly" ></textarea>
    <div style="position: relative;">User name: <param id="username"  type="text"  style="position: relative">< </div>
    <div style="color: #36b689; position: relative" id="status"  >status:  offline </div>
    <div style="position: relative;">
        <input id="message" type="text" width="280"  placeholder="type message" style="position: relative" />
        <button id="sendButton" onclick="sendMessage()">Send</button>
        <button id="closeButton" onclick="onClose(), hideCloseBut(), hideSendBut(), showConnBut(),hideMessageInput(), changeStatusOffline()">Exit chat</button>
        <button id="connectButton" onclick="connectme(), hideConnBut(),showCloseBut(),showSendBut(),showMessageInput(), changeStatusOnline()">Connect</button>
    </div>
</div>




<script type="text/javascript">

    let goButton = document.querySelector('.but');
    var cont = document.querySelector('.chat-box');

    goButton.addEventListener('click', function (e) {
        cont.style.transform = 'translateY(370px)'
    })
</script>

<script>
    var messagesOutput, userNameInput, messageInput, socket;

    function connectme() {

        messagesOutput = document.getElementById("messages");
        userNameInput = document.getElementById("username");
        messageInput = document.getElementById("message");

        // Connect to the WebSocket server
        socket = new WebSocket("ws://" + window.location.hostname + ":" + window.location.port + "/restweb/chat");

        // Receive WebSocket messages
        socket.onmessage = function (message) {
            messagesOutput.value += message.data + '\r';
        };

        var cls = document.getElementById("closeButton");
        cls.style.display = "block";

    }

    function onClose() {
        socket.close();
        //window.location.href = 'http://localhost:8080/restweb/home';
        document.getElementById("connectButton").style.display = 'inline';
    }

    function sendMessage() {
        var userName = userNameInput.value == '' ? "[Anonymous] " : '[' + userNameInput.value + '] ';
        socket.send(userName + (messageInput.value));
        messageInput.value = "";
        messageInput.focus();
    }

    function hideConnBut() {
        document.getElementById("connectButton").style.display = 'none';
    }

    function showConnBut() {
        document.getElementById("connectButton").style.display = 'inline';
    }


    function hideCloseBut() {
        document.getElementById("closeButton").style.display = 'none';
    }

    function showCloseBut() {
        document.getElementById("closeButton").style.display = 'inline';
    }

    function hideSendBut() {
        document.getElementById("sendButton").style.display = 'none';

    }

    function showSendBut() {
        document.getElementById("sendButton").style.display = 'inline';
    }

    function hideMessageInput(){
        document.getElementById("message").style.display = 'none';
    }

    function showMessageInput(){
        document.getElementById("message").style.display = 'inline';
    }

    // function changeStatusOnline() {
    //     document.getElementById("status").textContent = 'status: ONLINE';
    //     document.getElementById("status").style.color = 'green';
    //     var soundLink2 = $("#saber")[0];
    //     soundLink2.play();
    // }
    //
    // function changeStatusOffline(){
    //     document.getElementById("status").textContent = 'status: OFFLINE';
    //     document.getElementById("status").style.color = 'red';
    //
    //
    // }

</script>
<script src="resources/js/js-audio/sound.js"></script>
<script src="resources/js/js-audio/sound2.js"></script>
<script src="resources/js/js-audio/sound3.js"></script>
<script src="resources/js/js-audio/sound4.js"></script>


<audio src="dynamic/sounds/system/jbl.mp3" autoplay="autoplay" hidden="hidden" class="b"></audio>



</body>
</html>