messagesOutput = document.getElementById("messages");
userNameInput = document.getElementById("username");
messageInput = document.getElementById("message");

// Connect to the WebSocket server
socket = new WebSocket("ws://" + window.location.hostname + ":" + window.location.port + "/restweb/chat");