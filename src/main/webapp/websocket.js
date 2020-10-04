var messagesOutput, userNameInput, messageInput, socket;

window.addEventListener("load", function () {
        messagesOutput = document.getElementById("messages");
        userNameInput = document.getElementById("username");
        messageInput = document.getElementById("message");

        // Connect to the WebSocket server
        socket = new WebSocket("ws://" + window.location.hostname + ":" + window.location.port + "/restweb/endpoint");

        // Receive WebSocket messages
        socket.onmessage = function (message) {
            messagesOutput.value += message.data + '\r';
        };
    },
    false);

// Send WebSocket messages
function sendMessage() {
    var userName = userNameInput.value == '' ? "[Anonymous] " : '[' + userNameInput.value + '] ';
    socket.send(userName + JSON.stringify(messageInput.value));
    messageInput.value = "";
    messageInput.focus();
}

function onClose() {
    socket = null;
    window.location.href = 'http://localhost:8080/restweb/home';
}