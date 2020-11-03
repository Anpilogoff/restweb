
<html>
<head>
    <!-- CSS only -->
    <link rel="stylesheet" href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/css/bootstrap.min.css" integrity="sha384-JcKb8q3iqJ61gNV9KGb8thSsNjpSL0n8PARn9HuZOnIxN0hoP+VmmDGMN5t9UJ0Z" crossorigin="anonymous">

     JS, Popper.js, and jQuery
    <script src="https://code.jquery.com/jquery-3.5.1.slim.min.js" integrity="sha384-DfXdz2htPH0lsSSs5nCTpuj/zy4C+OGpamoFVy38MVBnE+IbbVYUew+OrCXaRkfj" crossorigin="anonymous"></script>
    <script src="https://cdn.jsdelivr.net/npm/popper.js@1.16.1/dist/umd/popper.min.js" integrity="sha384-9/reFTGAW83EW2RDu2S0VKaIzap3H66lZH81PoYlFhbGU+6BZp6G7niu735Sk7lN" crossorigin="anonymous"></script>
    <link href="https://stackpath.bootstrapcdn.com/bootstrap/4.5.2/js/bootstrap.min.js"
          integrity="sha384-B4gt1jrGC7Jh4AgTPSdUtOBvfO8shuf57BaghqFfPlYxofvL8/KUEfYiJOMMV+rV" crossorigin="anonymous"/>
    <style>

        body {
            position: absolute;
            height: 100%;
            background-image: url("resources/w.jpg");
        }



        form {
            position: relative;
            width: 280px;
            height: 200px;
        }



        table{
            position: relative;
        }



        #unknown{
            position: relative;
            box-shadow: #2b542c 5px 5px 15px 15px;
            width: 150px;
            height: 200px;
        }



        .container-fluid{
            position: relative;
            opacity: 50%;
            color: black;
        }


        /*nav {*/
        /*    opacity: 50%;*/
        /*    top: 40px;*/
        /*    position: relative;*/
        /*}*/



        body {
            height: 100vh;
            justify-content: center;
            align-items: center;
            text-align: center;
            position: absolute;
        }

        header{
            position: relative;
            opacity: 40%;
            width:1900px;
            height: 60px;
            background-color: white;
        }

        /*ul {*/
        /*    width: 1900px;*/
        /*    padding-left: 1px;*/
        /*    position: relative;*/
        /*}*/

        /*li {*/
        /*    position: relative;*/
        /*    display: inline-grid;*/
        /*    padding: 6px 0;*/
        /*    margin: 2px;*/

        /*}*/

        a {
            margin-right: 50px;
            --fill-color: #e6dc19;
            padding: 4px 0;
            font: 500 3rem Raleway, sans-serif;
            text-decoration: #e6dc19;
            -webkit-text-stroke: 2px var(--fill-color);
            background: linear-gradient(var(--fill-color) 0 100%) left / 0 no-repeat;
            color: yellow;
            border-color: black;
            border-width: 2px;
            
            transition: 0.5s linear;


        }


        /*ul{*/
        /*    text-decoration: none;*/
        /*    position: relative;*/
        /*    background-color: black;*/
        /*    padding-left: 0;*/

        /*    opacity: 50%;*/
        /*}*/
        /*li {*/
        /*    position: relative;*/
        /*    border-width: 3px;*/
        /*    border-radius: 6px;*/
        /*    background: #a3a3d4;*/
        /*    box-shadow: inset 2px 2px;*/
        /*    margin-left: 3px;*/
        /*    padding-left: 10px;*/
        /*    float: left;*/
        /*    margin-right: 5px;*/
        /*}*/

        /*a {*/
        /*    position: relative;*/
        /*    border-width: 3px;*/
        /*    border-radius: 5px;*/
        /*}*/




        /*.button.music1{*/
        /*    position: relative;*/
        /*    float: left;*/

        /*    left: 2%;*/
        /*    box-shadow: inset #e0d8d8 2px 2px;*/
        /*    border-width: 2px;*/
        /*    border-color: #d5930f;*/
        /*}*/


        /*.button.music2{*/
        /*    position: relative;*/
        /*    float: left;*/
        /*    left: 2%;*/
        /*    box-shadow: inset #e0d8d8 2px 2px;*/
        /*    border-width: 2px;*/
        /*    border-color: #d5930f;*/
        /*}*/

        /*.button.music3{*/
        /*    position: relative;*/
        /*    float: left;*/
        /*}*/

        /*.button.music4{*/
        /*    position: relative;*/
        /*    float: left ;*/
        /*}*/



        #navbar ul {
            display: none;
            position: absolute;

        }
        #navbar li:hover ul { display: block; }
        #navbar, #navbar ul {
            background: white;


            margin: 0;
            padding: 0;
            list-style-type: none;
        }
        #navbar {
            opacity: 40%;
            width: 2200px;
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
        #sendButton{
            display: none;
        }

    </style>

    <script type="text/javascript" src="https://code.jquery.com/jquery-3.5.1.slim.min.js"></script>
    <script type="text/javascript" src="https://atuin.ru/demo/sound/loudlinks.min.js"></script>


</head>


<body>
<audio src="dynamic/sounds/system/jbl.mp3" hidden autoplay="autoplay"></audio>

<!--<header class="head_menu" style="background-color: rgba(255,255,255,0.91); opacity: 50%; box-shadow:inset black  5px 5px; z-index: 0">;-->
<!--<ul id="navbar">-->
<!--    <li>-->
<!--    <div class="button music1" style=" float: left;">-->
<!--        <a href="chat.jsp">CHAT</a>-->
<!--    </div>-->

<!--    <audio src="resources/2.mp3" id="sound-link1" preload="auto"></audio>-->

<!--    </li>-->

<!--    <li>-->
<!--    <div class="button music2" style=" float: left;">-->
<!--        <a href="#">Friend</a>-->
<!--    </div>-->
<!--    <audio src="resources/1.mp3" id="sound-link2" preload="auto"></audio>-->
<!--    </li>-->

<!--    <li>-->
<!--    <div class="button music3" style="float: left;">-->
<!--        <a href="#">About us</a>-->

<!--    </div>-->
<!--    <audio src="resources/6.wav" id="sound-link3" preload="auto"></audio>-->
<!--    </li>-->


<!--    <li>-->
<!--    <div class="button music4" style=" float: left;">-->

<!--        <a href="#">Contacts</a>-->


<!--    </div>-->

<!--    <audio src="resources/1.1.wav" id="sound-link4" preload="auto"></audio>-->

<!--    </li>-->

<!--    <li>-->
<!--        <ul>-->
<!--            <li>-->
<!--                <iframe>sss</iframe>-->
<!--            </li>-->
<!--        </ul>-->
<!--    </li>-->
<!--</ul>-->

<!--</header>-->


<ul id="navbar">
<!--    <li><a href="chat.jsp">CHAT</a></li>-->
    <li><a href="#">1</a></li>
    <li><a href="#">chat</a>
        <ul>
            <li value="chat">

                <div><textarea id="messages" cols="60" rows="20" readonly="readonly"></textarea></div>
                <div>User name: <param id="username"  type="text" size="20"  autofocus value="<%= nickname%>" name="username"><%= nickname%> </param></div>
                <div>Message: <input id="message" type="text" size="60"/>
                    <button id="sendButton" onclick="sendMessage()">Send</button>
                    <button id="closeButton" onclick="onClose(),hideCloseBut(),hideSendBut(),showConnBut()" >Exit chat</button>
                    <button id="connectButton" onclick="connectme(), hideConnBut(),showCloseBut(),showSendBut()">Connect</button>
                </div>

            </li>
        </ul>
    </li>
    <li><a href="#">3</a></li>
</ul>

    <form class="avatar_form" name="avatar_form" enctype='multipart/form-data' method="post" action="uploadservlet"
          style="position: relative; left: 1250px">
            <img src="resources/w.jpg" datatype="img/*" id="unknown">
        <tr>
            <td><input type="file" accept="image/*" id="x" name="avatar"><input type="submit" value="send photo" id="submit_button" name="picss"></td>
        </tr>
    </form>



<!--<form class="music-form" name="music_form" enctype="multipart/form-data" method="post" action="musicUpload">-->
<!--    <input type="file" accept="multipart/form-data" name="song">-->
<!--    <input type="submit" value="save" id="save_button">-->
<!--</form>-->


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

      var cls =  document.getElementById("closeButton");
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

</script>
<script src="sound.js"></script>
<script src="sound2.js"></script>
<script src="sound3.js"></script>
<script src="sound4.js"></script>
</body>

</html>