<%@ page import="java.io.PrintWriter" %>
<%@ page import="am.basic.jdbcStart.controller.RegisterServlet" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>

<%
    if (request.getAttribute("message") != null) {
        String s = (String) request.getAttribute("message");
        response.getWriter().write(s);
    }
%>

<br>

<form method="post" action="/reg">
    <label for="fname">FirstName:</label><br>
    <input type="text" id="fname" name="name"><br>
    <label for="surn">LastName</label><br>
    <input type="text" id="surn" name="surname"><br>
    <label for="user">UserName</label><br>
    <input type="text" id="user" name="username"><br>
    <label for="pass">Password</label><br>
    <input type="text" id="pass" name="password"><br>
    <input type="submit" name="Submit">
</form>


</body>
</html>
