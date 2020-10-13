<%@ page import="com.anpilogoff.model.entity.User" %>
<%@ page import="com.google.gson.JsonElement" %>
<%@ page import="com.google.gson.Gson" %>
<!DOCTYPE html>
<html>
<% Gson gson = new Gson();
    User user = gson.fromJson((JsonElement) request.getSession(false).getAttribute("user"), User.class);
    String nickname = user.getNickname();
%>


<body style="text-align: center">
<br/>
<div><textarea id="messages" cols="80" rows="20" readonly="readonly"></textarea></div>
<div>User name: <param id="username"  type="text" size="20"  autofocus value="<%= nickname%>" name="username"><%= nickname%> </param></div>
<div>Message: <input id="message" type="text" size="60"/>
    <button onclick="sendMessage()">Send</button>
    <button onclick="onClose()" >Exit chat</button>
</div>
<script src="websocket.js"></script>

</body>
</html>