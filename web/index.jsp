<%--
  Created by IntelliJ IDEA.
  User: sky
  Date: 20.06.2020
  Time: 11:23
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
  <head>
    <title>Welcome</title>
  </head>
  <body>
  <form action="/login" method="post">
    <input type="text" name="email" placeholder="Input email"required><br>
    <input type="password" name="password" placeholder="Input password"required><br>
    <input type="submit" value="Login">
  </form>
  </body>
</html>
