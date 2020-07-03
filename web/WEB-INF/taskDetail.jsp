<%@ page import="model.Task" %>
<%@ page import="model.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>detail</title>
    <%--    <link rel="stylesheet" type="text/css" href="../css/style.css">--%>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    Task task = (Task) request.getAttribute("taskDetail");
    List<Comment> comments = (List<Comment>) request.getAttribute("taskComments");
%>
<div class="detail">
    Name: <%=task.getName()%><br>
    Description: <%=task.getDescription()%><br>
    Deadline: <%=task.getDeadline()%><br>
    Status: <%=task.getTaskStatus()%><br>
    UserName: <%=task.getUser().getName()%> <%=task.getUser().getSurname()%><br>
</div>
<div>
    <form action="/addComment" id="addComment" method="post">
        <input type="hidden" id="hidden" name="taskId" value="<%=task.getId()%>">
        <textarea name="comment" id="comment" placeholder="add comment"></textarea><br>
        <input type="submit" value="OK"><br>
    </form>

    <% for (Comment comment : comments) { %>
    <p><%=comment.getComment()%>
    </p><br>
    <%=comment.getUser().getName()%> <%=comment.getUser().getSurname()%> <%=comment.getDate()%><br>
    <% if (comment.getUser().equals(user)) {%>
    <a href="/removeComment?id=<%=comment.getId()%>">Delete</a><br>
    <% }
    } %>
    <div id="commentList">
</div>
</div>

<script src="../js/jquery-3.5.1.mun.js" type="text/javascript"></script>
<script>
    // $(document).ready(function () {
    //
    //     $("#addComment").submit(function (e) {
    //         e.preventDefault();
    //         let hidden = $("#hidden").val();
    //         let comment = $("#comment").val();
    //         $.ajax({
    //             url: "/taskDetail?hidden=" + hidden + "&comment=" + comment,
    //             method: "POST",
    //             success: function (result) {
    //                 $("#commentList").html(result)
    //             }
    //
    //         })
    //     })
    // })


</script>
</body>
</html>
