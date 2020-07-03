<%@ page import="model.Task" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Home</title>
    <link rel="stylesheet" type="text/css" href="../css/style.css">
</head>
<body>

<%

    User user = (User) session.getAttribute("user");
%>
<div class="welcome">
    <%=user.getName()%> <%=user.getSurname()%>
</div>
<div class="picture"><% if (user.getPictureUrl() != null) {%>
    <img src="/image?path=<%=user.getPictureUrl()%>" style="width: 300px;"/> <%}%>
</div>
<div>
    <form class="userimg" action="/updateImg" method="post" enctype="multipart/form-data">
        <input type="file" name="image"> <br>
        <input type="submit" value="Add or update img"><br>
    </form>
</div>
<div class="logout" id="log1">
    <a href="/logout" style="color: white">Logout</a><br>
</div>


<div class="main" id="taskList">


</div>
<script src="../js/jquery-3.5.1.mun.js" type="text/javascript"></script>
<script>
    $(document).ready(function () {
       let getTaskList = function () {

        $.ajax({
            url: "/taskList",
            method: "GET",
            success: function (result) {
              $("#taskList").html(result)
            }
        })
        };
       getTaskList();
       setInterval(getTaskList, 2000)
    })
</script>
</body>
</html>
