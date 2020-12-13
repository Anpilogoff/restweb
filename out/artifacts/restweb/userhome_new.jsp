<html>
<head>
    <title>Title</title>
    <meta charset="utf-8" type="text/css; text/html">
    <!--    <meta http-equiv="Content-Type" content="text/html; charset=utf-8; test/javascript">-->
    <link rel="stylesheet"  href="resources/css/userhomestyles.css">
    <link rel="stylesheet" href="https://cdnjs.cloudflare.com/ajax/libs/font-awesome/4.7.0/css/font-awesome.min.css">



    <link media="screen" href="resources/css/demo.css" type="text/css" rel="stylesheet" />
    <link rel="stylesheet" type="text/css" href="resources/css/component.css" />


</head>
<body>


<button id="scroll-up"><i class="fa fa-arrow-circle-o-up"> </i></button>


<div class="ul" id="ul">
    <input type="file" accept="audio/*" id="thefile2" style="position: relative; width: 140px; /*height: 40px */">

    <button id="music-button" onclick="showMusic()"><i class="fa fa-music" style="font-size: 25px"></i></button>


    <button id="but" onclick="show()"><i class="fa fa-envelope-o" style="font-size: 25px" id="chat-icon"></i></button>
    <!-- id="but" */-->

    <button class="contacts-button" id="m" type="button"><i class="fa fa-address-card"></i></button>


    <button class="question-button" id="mm" type="button"><i class="fa fa-question"></i></button>
    <button id="gear-button"><i class="fa fa-gear"></i></button>

    <div style="position: relative;
top: 35%; width: 100%; height: 50%" >

    <canvas id="canvas3"
            style="opacity:100%; position: relative; width:100%; height: 100%;   top: -2%;  filter: blur(1px); ">

    </canvas>
    </div>

    <div class="avatar">
        <form class="avatar-upload-form" enctype="multipart/form-data" method="post" action="uploadservlet">
            <input type="file" accept="image/*" id="choose-file-button" onclick="lbl()">
            <label for="choose-file-button" onclick="lbl()"><i class="fa fa-refresh" onclick="lbl()"></i> </label>
            <input type="submit" value="send photo" id="submit_button" name="picss">
            <label for="submit_button" id="lbl"><i class="fa fa-upload"></i> </label></input>
        </form>

    </div>




</div>


<div class="chat-box" id="chat-box">
    <div style="position: relative">User name:
        <param id="username" type="text"><!-- username --> </div>
    <div style="position: relative"><p id="status">OFFLINE</p></div>
    <input id="message" type="text" width="280" placeholder="type message">
    <button id="sendButton" onclick="sendMessage()"><i class="fa fa-wechat"></i></button>
</div>




<canvas id="canvas2"
        style="z-index: 3; opacity: 90%; position:relative; width:100%; height:6%; filter: blur(0px); float: left; top: 7%; background-color: #171717;   transform: rotateX(-180deg); overflow: hidden">
</canvas>




<canvas id="canvas" style=" display: none; position:relative; transform: rotateY(180deg);">

</canvas>




        <div id="large-header" class="large-header" >
            <canvas id="demo-canvas" style="width: 100%; height:100%; background-color: #171717; opacity: 70%"></canvas>




    </div>




</div>


        <script src="resources/js/demo-2.js"></script>


<audio controls id="audio2" hidden></audio>


<%--        <button id="backward-button" class="player-buttons"><i class="fa fa-backward"></i></button>--%>
<%--        <button id="play-button" class="player-buttons"><i class="fa fa-play-circle"></i></button>--%>
<%--        <button id="forward-button" class="player-buttons"><i class="fa fa-forward"></i></button>--%>


<script>
    window.onload = function () {
    }
    var file = document.getElementById("thefile2");
    var audio = document.getElementById("audio2");
    file.onchange = function () {
        var files = this.files;
        audio.src = URL.createObjectURL(files[0]);
        audio.load();
        audio.play();

        var context = new AudioContext();
        var src = context.createMediaElementSource(audio);
        var analyser = context.createAnalyser();

        var canvas = document.getElementById("canvas");
        canvas.width = window.innerWidth;
        canvas.height = window.innerHeight;
        var ctx = canvas.getContext("2d");
        src.connect(analyser);
        analyser.connect(context.destination);
        analyser.fftSize = 512;

        var bufferLength = analyser.frequencyBinCount;
        var dataArray = new Uint8Array(bufferLength);

        var WIDTH = canvas.width;
        var HEIGHT = canvas.height;
        var barWidth = (WIDTH / bufferLength)/3;  // дели на 2
        var barHeight;
        var x = 0;

        function renderFrame() {
            requestAnimationFrame(renderFrame);
            x = 0;
            var canvas2 = document.getElementById("canvas2");
            var canvas3 = document.getElementById("canvas3");
            canvas2.width = window.innerWidth;
            canvas2.height = window.innerHeight * 1.5;

            canvas3.width = window.innerWidth;
            canvas3.height = window.innerHeight;
            var ctx2 = canvas2.getContext("2d");

            var ctx3 = canvas3.getContext("2d");
            analyser.getByteFrequencyData(dataArray);
            ctx2.fillStyle = '#171717';
            ctx2.fillRect(10, -10, WIDTH, HEIGHT);
            ctx3.fillStyle = 'rgba(23,23,23,0)';
            ctx3.fillRect(30, -20, WIDTH, HEIGHT);

            for (var i = 0; i < bufferLength; i++) {
                barHeight = dataArray[i]*4;
                var r = barHeight + (510 * (i / bufferLength));
                var g = 331 * (i / bufferLength);
                var b = 810;
                // ctx.fillStyle = "rgb(" + r + "," + r + "," + b + ")";
                // ctx.fillRect(x*2, HEIGHT- barHeight, barWidth*2, barHeight-40);
                // ctx2.fillRect(x, HEIGHT - barHeight, barWidth, barHeight);
                ctx2.fillStyle = "rgb(" + g + "," + b + "," + b + ")"; //brr - white  //rrg- yellow //gbb-blue(goluboi(
                ctx2.fillRect(x *3, HEIGHT - barHeight, barWidth / 0.5, barHeight-20 );
                ctx3.fillStyle = "#35d9e8" //##27d788;  02b4bb
                ctx3.fillRect(x *3, HEIGHT - barHeight, barWidth , barHeight -10);
                x += barWidth +1 ;
            }
        }
        audio.play();
        renderFrame();
    };
</script>
<%--    <script type="text/javascript" src="resources/js/test2.js"></script>--%>

</body>
</html>
