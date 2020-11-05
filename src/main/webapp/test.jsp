<html>
<head>
<style>


    .container-1{
        border-color: #232323;
        position: absolute;
        background-color: #232323;
        width: 100%;
        height: 50px;
    }

    .sliders{
        top: 27px;
        position: absolute;
        width: 100%;
        background-color: red;
    color: greenyellow}

    .slider-1{
        margin-left: 120px;
        position: relative;
        background-color: #232323;
        width: 100px;
        border-width: 0px;
        color: #0c9a11;

    }

    li {
        float: left;
    }
</style>

</head>

<body>

<ul>
    <li>
        <div>
            <a href="#" class="slider-1">chat</a>
        </div>
    <li  value="chat" style=" background-color: #848e84; opacity: 60%; box-shadow: inset black 1px 1px 4px 3px;">
        <div><textarea id="messages" cols="60" rows="20" readonly="readonly" style=" border-width:4px; border-color: darkslategrey; border-radius: 15px; background-color: darkslategrey; opacity: 70%; box-shadow: inset black 1px 1px 3px 3px;" ></textarea></div>
        <div>User name: <param id="username"  type="text" size="20"  autofocus > </param></div>
        <div id="status" style="color: red">offline </div>
        <div style="height: 50px; "><input id="message" type="text" size="60"  placeholder="type message" />
            <button id="sendButton" onclick="sendMessage()">Send</button>
            <button id="closeButton" onclick="onClose(), hideCloseBut(), hideSendBut(), showConnBut(),hideMessageInput(), changeStatusOffline()">Exit chat</button>
            <button id="connectButton" onclick="connectme(), hideConnBut(),showCloseBut(),showSendBut(),showMessageInput(), changeStatusOnline()">Connect</button>
        </div>

    </li>


    <li >
        <div>
            <a href="#" class="slider-1">chat</a>
        </div>

    </li>

    <li >
        <div>
            <a href="#" class="slider-1">chat</a>
        </div>

    </li>

</ul>







</body>
</html>