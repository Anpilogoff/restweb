<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.anpilogoff.model.entity.User" %>
<%@ page import="com.google.gson.JsonElement" %>
<%@ page import="java.io.OutputStream" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html datatype="img/*">
<head>
    <meta http-equiv="Content-Type" content="image/*; multipart/form-data; charset=utf-8">
    <style>

        html{
            width: 100%;
            height: 100%;
        }


        body {
            background-image: url("resources/w.jpg");

            height: 100%;
            width: 100%;
            justify-content: center;
            align-items: center;
            text-align: center;
            position: absolute;
        }


        a {
            margin-right: 50px;
            --fill-color: #0c9a11;
            padding: 4px 0;
            font: 500 3rem Raleway, sans-serif;
            text-decoration: #e6dc19;
            -webkit-text-stroke: 2px var(--fill-color);
            background: linear-gradient(var(--fill-color) 0 100%) left / 0 no-repeat;
            color: yellow;

            transition: 0.1s linear;


        }



        #navbar ul {
            display: none;
            position: absolute;

        }
        #navbar li:hover ul { display: block; }
        #navbar, #navbar ul {
            /*background: white;*/


            margin: 0;
            padding: 0;
            list-style-type: none;
        }
        #navbar {
            opacity: 40%;
            width: 100%;
            height: 60px;
        }
        #navbar li {
            float: left;
            position: relative;
        }
        #navbar ul li {opacity: 100%; float: none; }

        #closeButton{
            display: none;
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

        #unknown{
            position: relative;
            box-shadow: #2b542c 5px 5px 15px 15px;
            width: 150px;
            height: 200px;
        }
        form {
            position: relative;
            width: 280px;
            height: 200px;
        }



        table{
            position: relative;
        }


    </style>

    <%
        Gson gson = new Gson();
        User user = gson.fromJson((JsonElement) request.getSession(false).getAttribute("user"), User.class);
        String avatar = (String) request.getSession(false).getAttribute("avatar");
        String nickname = user.getNickname();
        String img_bytes = (String) request.getSession(false).getAttribute("avatar_bytes");
    %>
    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>

</head>
<audio src="dynamic/sounds/system/jbl.mp3" hidden autoplay="autoplay"></audio>
<audio src="dynamic/sounds/system/saber_on.mp3" hidden id="saber"></audio>


<body>
<ul id="navbar" style="background: rgba(92,91,91,0.6); opacity: 100%">
    <li>
        <div class="button music1">
            <a href="#">Music</a>
        </div>

        <ul>
            <li><div>
                <form name="music_form" method="post" action="musicUpload" style="height: 10px">
                </form>
                    <div>

                        <audio controls src="resources/1.mp3"></audio>
                        <audio controls src="resources/1.mp3"></audio>
                        <audio controls src="resources/1.mp3"></audio>

                        <audio controls src="resources/1.mp3"></audio>

                    </div>

            </div>
            </li>
        </ul>
    </li>
    <audio src="resources/2.mp3" id="sound-link1" preload="auto"></audio>


    <li>
        <div class="button music2">
            <a href="#">CHAT</a>
        </div>

        <ul>
            <li value="chat" style=" background-color: #848e84; opacity: 60%; box-shadow: inset black 1px 1px 4px 3px;">
                <div><textarea id="messages" cols="60" rows="20" readonly="readonly"
                               style=" background-color: #0e572f; opacity: 80%; box-shadow: inset black 1px 1px 3px 3px;"></textarea>
                </div>
                <div>User name:
                    <param id="username" type="text" size="20" autofocus value="<%= nickname%>"
                           name="username"><%= nickname%>
                    </param>
                </div>
                <div id="status" style="color: red">offline</div>
                <div style="height: 50px; "><input id="message" type="text" size="60" placeholder="type message"/>
                    <button id="sendButton" onclick="sendMessage()">Send</button>
                    <button id="closeButton"
                            onclick="onClose(), hideCloseBut(), hideSendBut(), showConnBut(),hideMessageInput(), changeStatusOffline()">
                        Exit chat
                    </button>
                    <button id="connectButton"
                            onclick="connectme(), hideConnBut(),showCloseBut(),showSendBut(),showMessageInput(), changeStatusOnline()">
                        Connect
                    </button>
                </div>

            </li>
            <audio src="resources/1.mp3" id="sound-link2" preload="auto"></audio>

        </ul>
    </li>
    <li>
        <div class="button music3">
            <a href="#">Contacts</a>
        </div>
    </li>
    <audio src="resources/6.wav" id="sound-link3" preload="auto"></audio>
</ul>


<form class="avatar_form" name="avatar_form" enctype='multipart/form-data' method="post" action="uploadservlet"
      style="position: relative; left: 1250px">
    <img src="resources/w.jpg" datatype="img/*" id="unknown">
    <tr>
        <td><input type="file" accept="image/*" id="x" name="avatar"><input type="submit" value="send photo" id="submit_button" name="picss"></td>
    </tr>
</form>







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

    function changeStatusOnline() {
        document.getElementById("status").textContent = 'online';
        document.getElementById("status").style.color = 'green';
        var soundLink2 = $("#saber")[0];
        soundLink2.play();
    }

    function changeStatusOffline(){
        document.getElementById("status").textContent = 'offline';
        document.getElementById("status").style.color = 'red';


    }

</script>
<script src="sound.js"></script>
<script src="sound2.js"></script>
<script src="sound3.js"></script>
<script src="sound4.js"></script>

</body>
</html>