<%@ page import="model.Task" %>
<%@ page import="java.util.List" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>

<%
    List<Task> tasks = (List<Task>) request.getAttribute("tasks");
%>
<h2 style="color: gainsboro">All Tasks </h2><br>
<table border="1"  >
    <tr style="color: white">
        <td>Name</td>
        <td>Description</td>
        <td>Deadline</td>
        <td>Status</td>
        <td>User</td>
        <td>Update Status</td>

    </tr>
    <%
        for (Task task : tasks) { %>
    <tr style="color: white">
        <td><a href="/taskDetail?taskId=<%=task.getId()%>" style="color: red"><%=task.getName()%></a>
        </td>
        <td><%=task.getDescription()%>
        </td>
        <td><%=task.getDeadline()%>
        </td>
        <td><%=task.getTaskStatus().name()%>
        </td>
        <td><%=task.getUser().getName() + " " + task.getUser().getSurname() %>
        </td>
        <td>
            <form action="/changeTaskStatus" method="post">
                <input type="hidden" name="taskId" value="<%=task.getId()%>">
                <select name="status">
                    <option disabled selected value>Assign type</option>
                    <option value="NEW">NEW</option>
                    <option value="IN_PROGRESS">IN_PROGRESS</option>
                    <option value="FINISHED">FINISHED</option>
                </select> <input type="submit" value="OK"><br>

            </form>
        </td>
    </tr>

    <%
        }
    %>
</table>

