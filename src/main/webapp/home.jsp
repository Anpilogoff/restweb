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
            background-image: url("/resources/ccc.jpg");
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
            text-align: center;
        }
        #x{
            left: 20px;
            width: 120px;
        }
        #submit_button{
            box-shadow:
                    0 0 0 1px #fce25b inset,
                    -1px 0px rgb(220,195,35), -1px 1px rgb(192,167,7),
                    -2px 1px rgb(219,194,34), -2px 2px rgb(191,166,6),
                    -3px 2px rgb(218,193,33), -3px 3px rgb(190,165,5),
                    -4px 3px rgb(217,192,32), -4px 4px rgb(189,164,4),
                    -5px 4px rgb(216,191,31), -5px 5px rgb(188,163,3),
                    -6px 5px rgb(215,190,30), -6px 6px rgb(187,162,2),
                    -7px 6px rgb(214,189,29), -7px 7px rgb(186,161,1),
                    -8px 7px rgb(213,188,28), -8px 8px rgb(185,160,0),
                    -7px 9px 1px rgba(0,0,0,.4),
                    -5px 11px 1px rgba(0,0,0,.2),
                    -1px 13px 4px rgba(0,0,0,.2),
                    4px 16px 7px rgba(0,0,0,.3);
            transition: .4s;
        }
        #submit_button:active {
            box-shadow: none;
            -webkit-transform: translate(-7px, 8px);
            transform: translate(-7px, 8px);
        }
        #submit_button:after {
            content: "";
            position: absolute;
            top: calc(50% - .6em/2);
            right: .6em;
            -webkit-transform: rotate(-45deg);
            transform: rotate(-45deg);
            border: .37em solid;
            border-top: none;
            border-left: none;
            width: .6em;
            height: .6em;
            box-shadow: inset -2px 2px rgba(255,255,255,.3);
        }
    </style>

    <% Gson gson = new Gson();
        User user =  gson.fromJson((JsonElement) request.getSession(false).getAttribute("user"), User.class);
        String file = (String) request.getSession(false).getAttribute("avatar");
        System.out.println(file);%>
</head>
<body>
<audio src="dynamic/sounds/system/jbl.mp3" autoplay="autoplay" hidden="hidden" class="b"></audio>

<form name="photo_upload" enctype='multipart/form-data' method="post" action="uploadservlet">
    <table><img src="resources/<%=file%>" datatype="img/*" id="unknown">

        <tr>
            <td><input type="file" multiple accept="image/*" id="x" name="avatar"></td><td><input type="submit" value="send photo" id="submit_button"></td>
        </tr>
    </table>



</form>

</body>
</html>