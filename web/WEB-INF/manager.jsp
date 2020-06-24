<%@ page import="model.Task" %>
<%@ page import="model.User" %>
<%@ page import="java.util.List" %><%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 20.06.2020
  Time: 11:26
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>home</title>
</head>
<body>
<a href="/logout">Logout</a>
<%List<User> users = (List<User>) request.getAttribute("users");%>
<%List<Task> tasks = (List<Task>) request.getAttribute("tasks");%>

<div style="width: 800px">
    <div style="width: 50%;float: left">
        Add User <br>
        <form action="/register" method="post" enctype="multipart/form-data">
            <input type="text" name="name" placeholder="name" required><br>
            <input type="text" name="surname" placeholder="surname" required><br>
            <input type="text" name="email" placeholder="email" required><br>
            <input type="password" name="password" placeholder="password" required><br>
            <select name="type">
                <option value="USER">USER</option>
                <option value="MANAGER">MANAGER</option>
            </select><br>
            <input type="file" name="image"> <br>
            <input type="submit" value="Register"><br>
        </form>
    </div>
    <div style="width: 50%;float: left">
        Add Task <br>
        <form action="/addTask" method="post"><br>
            <input type="text" name="name" placeholder="name"><br>
            <textarea name="description" placeholder="description"></textarea><br>
            <input type="date" name="deadline"><br>
            <select name="status">
                <option value="NEW">NEW</option>
                <option value="IN_PROGRESS">IN_PROGRESS</option>
                <option value="FINISHED">FINISHED</option>
            </select><br>
            <select name="user_id">
                <%
                    for (User user : users) { %>
                <option value="<%=user.getId()%>"><%=user.getName()%> <%=user.getSurname()%>
                </option>

                <%
                    }
                %>

            </select><br>
            <input type="submit" value="Add"><br>
        </form>
    </div>
</div>
<div>
    All Users <br>
    <table border="1">
        <tr>
            <td>Name</td>
            <td>Surname</td>
            <td>Email</td>
            <td>Type</td>
        </tr>
        <%
            for (User user : users) { %>
        <tr>
            <td><%=user.getName()%>
            </td>
            <td><%=user.getSurname()%>
            </td>
            <td><%=user.getEmail()%>
            </td>
            <td><%=user.getUserType().name()%>
            </td>
        </tr>

        <%
            }
        %>
    </table>
</div>
<div>
    All Tasks <br>
    <table border="1">
        <tr>
            <td>Name</td>
            <td>Description</td>
            <td>Deadline</td>
            <td>Status</td>
            <td>User</td>
        </tr>
        <%
            for (Task task : tasks) { %>
        <tr>
            <td><%=task.getName()%>
            </td>
            <td><%=task.getDescription()%>
            </td>
            <td><%=task.getDeadline()%>
            </td>
            <td><%=task.getTaskStatus().name()%>
            </td>
            <td><%=task.getUser().getName()+" "+task.getUser().getSurname()%>
            </td>
        </tr>

        <%
            }
        %>
    </table>
</div>

</body>
</html>
