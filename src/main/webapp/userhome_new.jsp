<html >
<head>
    <title>Title</title>
    <meta charset="utf-8" c="text/css">
<!--    <meta http-equiv="Content-Type" content="text/html; charset=utf-8; test/javascript">-->
    <link  href="resources/css/userhomestyles.css">
    <link rel="stylesheet" href="https:/cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">


</head>
<body>


<button id="scroll-up"><i class="fa fa-arrow-circle-o-up"> </i></button>

<div class="ul" id="ul">

    <button  id="music-button" onclick="showMusic()"> <i class="fa fa-music" style="font-size: 25px"></i></button>



    <button   id="but"  onclick="show()"><i class="fa fa-envelope-o" style="font-size: 25px" id="chat-icon"></i></button>
    <!-- id="but" */-->

    <button class="contacts-button" id="m" type="button"><i class="fa fa-address-card"></i></button>


    <button class="question-button" id="mm" type="button" ><i class="fa fa-question"></i></button>


    <button id="gear-button"> <i class="fa fa-gear" ></i> </button>



</div>

<div class="animated-box">

</div>





<div class="chat-box" id="chat-box">
    <textarea id="messages-area" readonly="readonly"></textarea>
    <div style="position: relative">User name:
        <param id="username" type="text"><!-- username --> </div>
    <div   style="position: relative" >  <p id="status">OFFLINE</p></div>
        <input id="message" type="text" width="280" placeholder="type message">
        <button id="sendButton" onclick="sendMessage()"><i class="fa fa-wechat"></i></button>
</div>
<div class="avatar">

    <form class="avatar-upload-form" enctype="multipart/form-data" method="post" action="uploadservlet">


            <input type="file"  accept="image/*" id="choose-file-button"  onclick="lbl()">
              <label for="choose-file-button" onclick="lbl()"  ><i class="fa fa-refresh" onclick="lbl()"></i> </label>


        <input type="submit" value="send photo" id="submit_button" name="picss">
               <label for="submit_button" id="lbl"><i class="fa fa-upload"></i> </label></input>

    </form>

</div>




<div class="bottom-block"></div>




        <button id="backward-button" class="player-buttons"><i class="fa fa-backward"></i></button>
        <button id="play-button" class="player-buttons"><i class="fa fa-play-circle"></i></button>
        <button id="forward-button" class="player-buttons"><i class="fa fa-forward"></i></button>



<script>

</script>

        <script type="text/javascript" src="resources/js/test2.js"></script>

</body>
</html>
