var messagesOutput, userNameInput;
var socket;
const getCookieValue = (name) => (
  document.cookie.match('(^|;)\\s*' + name + '\\s*=\\s*([^;]+)')?.pop()
);
var  messageInput;



function setMsgStyles(msg,arg){
    msg.style.color = 'rgb(0 255 98)';

    msg.style.display = 'block';
    msg.style.textShadow = '0px 0px 2px, black 2px 1px 3px, black -1px 4px 2px,black -1px 4px 4px';
    msg.style.transform = 'translate3d('+ arg +'px, 7px, 7px)';
    msg.style.transition = '0.5s ease-out';
}

var len = getCookieValue("user_nickname").length;
function connectLiveChat() {
        users = document.getElementById('users-online');
        messagesOutput = document.getElementById("chat-box");
        userNameInput = document.getElementById("username");
        messageInput = document.getElementById("message");
        chat = document.getElementById('chat-box');

        // Connect to the WebSocket server
        socket = new WebSocket("ws://localhost:8080/restweb/chat?name="+getCookieValue("user_nickname")); //тут не куки value а параметр из личной карточки"

        // Receive WebSocket messages

    var msg;
        socket.onmessage = function (message) {

            if( message.data.type === "base64"){
                // alert(aga);
                // alert(typeof(message.data));
            }
            if(message.data.startsWith(" ~ ") &&  message.data.substring(9,9+len) === getCookieValue("user_nickname") ) {
                msg = document.createElement('span');
                msg.textContent = message.data;
                msg.style.position = 'relative';
                setMsgStyles(msg,300);

                users.append(msg);
                users.append("\r");
                setTimeout(function () {
                    msg.style.transform = 'translate3d(10px, 7px, 7px)';

                }, 100);

                users.scrollTop = users.scrollHeight;
            }else if(message.data.startsWith(" ~ ") && message.data.substring(9,9+len) !== getCookieValue("user_nickname")){
                msg = document.createElement('span');


                chat.append(msg);
                chat.append("\r");
                setMsgStyles(msg,-300);
                msg.innerText = message.data;
                setTimeout(function () {
                    msg.style.transform = 'translate3d(10px,7px,7px)';
                    }
                );

            }else{
                var block = document.createElement('div');
                var nickName = document.createElement('span');
                nickName.textContent = getCookieValue("user_nickname");
                alert(getCookieValue("user_nickname"));
                nickName.style.position = 'relative';
                nickName.style.width = '50px';
                nickName.style.height = '10px';
                nickName.style.color = '#00ffba';
                nickName.style.textShadow = '0px 0px 2px, -3px -1px 6px black, -2px 2px 1px black';
                nickName.style.font = 'Open Sans';



                block.style.width="100px";
                block.style.height = "25px";
                block.style.position = 'relative';
                users.appendChild(block);
                block.style.boxShadow = 'rgb(0 255 139) 0px 0px 3px 2px';
                block.style.borderRadius = '10px';
                block.style.perspective = '300px';
                block.style.top = '3%';


                var im = document.createElement('img');
                im.src = "data:image/png; base64, " + message.data;
                im.style.position = 'relative';
                im.style.height = '25px';
                im.style.width = '25px';
                im.style.borderRadius = '12px';
                im.style.boxShadow = '0px 0px 2px 1px green';

                setTimeout(function () {
                    im.style.boxShadow = '0px 0px 2px 1px #00ff68'; im.style.animation = 'size 2s linear';
                },2000);
                im.style.transition = '2s linear';
                im.style.animation = 'connected 1s ease-in-out';
                block.appendChild(im);

                block.appendChild(nickName);

            }


            // var msgElem = document.createElement('span');
            //var msgString = message.data;
            //msgString = msgString.slice(0,21);

};



        socket.onclose = function (event){
            if(event.wasClean){

                closeMsg = document.createElement('div');
                closeMsg.innerHTML = 'you have successfully disconnected from public chat';
                chat.append(closeMsg);

                  
                }
            };
        }


// Send WebSocket messages
function sendMessage() {
    // socket.send(getCookieValue("user_nickname") + (messageInput.value));
    socket.send(messageInput.value);
    messageInput.value = "";
    messageInput.focus();
};

function closeWebSocket() {
    socket.close(1000, "successfully disconnected");
};







