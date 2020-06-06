<%@ page import="static am.basic.jdbcStart.util.Constants.ParameterKeyByClient.PARAMETER_USERNAME_KEY" %>

<%@ page import="am.basic.jdbcStart.service.UserManager" %><%--
  Created by IntelliJ IDEA.
  User: Grigor
  Date: 02-Jun-20
  Time: 10:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
​<% if (request.getAttribute("message") != null) {%>
<% String s = (String) request.getAttribute("message");%>
<% response.getWriter().write(s);%>
<%}%>

<form method="post" action="/verify">
    <input type="hidden" name="username" value="<%=request.getAttribute(PARAMETER_USERNAME_KEY)%>">
    code: <input type="text" name="code"><br>
    <input type="submit" name="submit"><br>
</form>




<br><br>
<form method="post" action="/resend">
    <input type="hidden" name="username" value="<%=request.getAttribute(PARAMETER_USERNAME_KEY)%>">
    <input type="submit" name="submit" value="Resend code"><br>
</form>



</body>
</html>
