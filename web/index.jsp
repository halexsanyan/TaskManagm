<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Welcome</title>
    <link rel="stylesheet" type="text/css" href="css/style.css">
</head>
<body>
<%
    String msg = "";
    if (session.getAttribute("msg") != null) {
        msg = (String) session.getAttribute("msg");
        session.removeAttribute("msg");
    }

%>
<p style="color: red">
    <%=msg%>
</p>
<div>
    <form class="box" action="/login" method="post">
        <h1>Login</h1>
        <input type="text" name="email" placeholder="Email" required>
        <input type="password" name="password" placeholder="Password" required>
        <input type="submit" name="" value="Login">
    </form>

</div>
</body>
</html>
