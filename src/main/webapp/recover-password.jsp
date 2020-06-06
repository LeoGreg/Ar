<%@ page import="static am.basic.jdbcStart.util.Constants.ParameterKeyByClient.PARAMETER_USER_ATTRIBUTE_KEY" %>
<%@ page import="static am.basic.jdbcStart.util.Constants.ParameterKeyByClient.PARAMETER_USERNAME_KEY" %><%--
  Created by IntelliJ IDEA.
  User: Grigor
  Date: 26-May-20
  Time: 12:49 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>


><% if (request.getAttribute("message") != null) { %>
<%=request.getAttribute("message")%>
<% } %>
<br><br>


<form method="post" action="/recover-password">
    <input type="hidden" name="username" value="<%=request.getAttribute(PARAMETER_USERNAME_KEY)%>">
    code: <input type="text" name="code"><br>
    new password : <input type="text" name="password"><br>
    <input type="submit" name="submit"><br>
</form>

</body>
</html>
