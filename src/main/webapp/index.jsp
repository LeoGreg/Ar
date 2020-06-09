<%@ page import="java.io.PrintWriter" %>
<%@ page import="am.basic.jdbcStart.controller.RegisterServlet" %>

<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
​<% if (request.getAttribute("message") != null) {%>
<% String s = (String) request.getAttribute("message");%>
<% response.getWriter().write(s);%>
<%}%>

<h1 style="background :darkcyan " align="center">enter information </h1><br/><br/>

<br><br>
<form method="post" action="/login">
    <label for="fname">Username:</label><br>
    <input type="text" id="fname" name="username"><br>
    <label for="pass">Password</label><br>
    <input type="password" id="pass" name="password"><br>
    remember:<input type="checkbox" name="remember">;
    <input type="submit" name="submit">
    ​<a href="forget-password.jsp">forget password?</a>
    <p>Registration Service <a href="registration.jsp">register</a></p>
</form>

<br>
<a href="/cook">test myCookie</a>
​
​
</body>
</html>