<%@ page import="com.google.gson.JsonObject" %>
<%@ page import="com.anpilogoff.model.entity.User" %>
<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.google.gson.JsonElement" %><%--
  Created by IntelliJ IDEA.
  User: aaanp
  Date: 27.09.2020
  Time: 17:57
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <style>
        body{
            position: absolute;
            background-image: url("/resources/w.jpg");
        }
        form {
            position: absolute;
            width: 280px;
            height: 200px;
        }
        table{
            position: absolute;
        }
        #unknown{
            position: absolute;
            box-shadow: #2b542c 5px 5px 15px 15px;
            margin-top: 65px;
            margin-left: 20px;
            width: 240px;
            height: 300px;
            text-align: center;
        }
        #x{
            left: 20px;
            width: 120px;
        }
    
    </style>

    <% Gson gson = new Gson();
        User user =  gson.fromJson((JsonElement) request.getSession(false).getAttribute("user"), User.class);
        String file = (String) request.getSession(false).getAttribute("avatar");
        ;%>
</head>
<body>
<audio src="dynamic/sounds/system/jbl.mp3" autoplay="autoplay" hidden="hidden" class="b"></audio>

<form name="photo_upload" enctype='multipart/form-data' method="post" action="uploadservlet">
    <table>
        <tr>
        <img src="dynamic/images/avatars/<%=user.getNickname()%>/<%=file%>" datatype="img/jpeg" id="unknown">

        </tr>
            <td><input type="file" multiple accept="image/*" id="x" name="avatar"></td><td><input type="submit" value="send photo" id="submit_button"></td>
        </tr>
    </table>
</form>

</body>
</html>