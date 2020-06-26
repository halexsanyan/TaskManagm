<%@ page import="model.Task" %>
<%@ page import="model.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="model.User" %>

<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>detail</title>
</head>
<body>
<%
    User user = (User) session.getAttribute("user");
    Task task = (Task) request.getAttribute("taskDetail");
    List<Comment> comments = (List<Comment>) request.getAttribute("taskComments");
%>
Name: <%=task.getName()%><br>
Description: <%=task.getDescription()%><br>
Deadline: <%=task.getDeadline()%><br>
Status: <%=task.getTaskStatus()%><br>
UserName: <%=task.getUser().getName()%> <%=task.getUser().getSurname()%><br>
<div>
    <form action="/addComment" method="post">
    <input type="hidden" name="taskId" value="<%=task.getId()%>">
    <textarea name="comment" placeholder="add comment"></textarea><br>
    <input type="submit" value="OK"><br>
    </form>

<% for (Comment comment : comments) { %>
<p><%=comment.getComment()%></p><br>
<%=comment.getUser().getName()%> <%=comment.getUser().getSurname()%> <%=comment.getDate()%><br>
<% if(comment.getUser().equals(user)){%>
<a href="/removeComment?id=<%=comment.getId()%>">Delete</a><br>
<% } } %>
</div>
</body>
</html>
