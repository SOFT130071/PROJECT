<%--
  Created by IntelliJ IDEA.
  User: Veronica
  Date: 7/26/18
  Time: 11:13 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title><%= request.getParameter("course")%> | MOOC</title>
    <link rel="stylesheet" href="css/reset.css">
    <link rel="stylesheet" href="css/coursepage.css">
</head>
<body>
<nav id="left"></nav>
<nav id="right"></nav>
<iframe id="player" src="<%= request.getParameter("link")%>" frameborder="0" allowfullscreen></iframe>
</body>
</html>
