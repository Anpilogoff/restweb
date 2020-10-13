<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.anpilogoff.model.entity.User" %>
<%@ page import="com.google.gson.JsonElement" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<html datatype="img/*">
<head>
    <meta http-equiv="Content-Type" content="image/*; multipart/form-data; charset=utf-8">
    <style>


        body{
            position: absolute;
            background-image: url("resources/w.jpg");
        }

        #logout{

        }

        #logout:hover{
            box-shadow: 0 0 1px 1px red;

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
            margin-top: 65px;
            margin-left: 20px;
            width: 240px;
            height: 300px;
        }


        #logout{
            position: relative;
            top: 150px;
            left: 1000px;
        }

    </style>

    <%
        Gson gson = new Gson();
        User user = gson.fromJson((JsonElement) request.getSession(false).getAttribute("user"), User.class);
        String avatar = (String) request.getSession(false).getAttribute("avatar");
        String nickname = user.getNickname();
        String img_bytes = (String) request.getSession(false).getAttribute("avatar_bytes");
    %>

</head>

<body>
<audio src="dynamic/sounds/system/jbl.mp3" autoplay="autoplay" hidden="hidden" class="b"></audio>

<form name="photo_upload" enctype='multipart/form-data' method="post" action="uploadservlet">
    <section>

        <tr>
            <td><a href="chat.jsp" onclick="">Let's CHAT</a></td>

            <td><a href="logout" id="logout">Log_out</a></td>
        </tr>


      <td>
            <img src="http://localhost:8080/restweb/dynamic/images/avatars/<%=nickname%>/<%=avatar%>" datatype="img/jpeg" id="unknown">
        </td>

        <!---dynamic/images/avatars/<%=nickname%>-    -->


    </section>
</form>



</body>
</html>