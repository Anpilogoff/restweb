<%@ page import="com.google.gson.Gson" %>
<%@ page import="com.anpilogoff.model.entity.User" %>
<%@ page import="com.google.gson.JsonElement" %>
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

    </style>cccccc

    <% Gson gson = new Gson();
        User user =  gson.fromJson((JsonElement) request.getSession(false).getAttribute("user"), User.class);
        String file = (String) request.getSession(false).getAttribute("avatar");
        System.out.println(file);%>
</head>
<body>
<audio src="dynamic/sounds/system/jbl.mp3" autoplay="autoplay" hidden="hidden" class="b"></audio>

<form name="photo_upload" enctype='multipart/form-data' method="post" action="uploadservlet">
    <table><img src="dynamic/images/avatars/<%=user.getNickname()%>/<%= file%>" datatype="img/*" id="unknown">

        <tr>
            <td><input type="file" multiple accept="image/*" id="x" name="avatar"></td><td><input type="submit" value="send photo" id="submit_button"></td>
        </tr>
    </table>



</form>

</body>
</html>