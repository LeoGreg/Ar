<%@ page import="static am.basic.jdbcStart.util.Constants.ParameterKeyByClient.PARAMETER_MESSAGE_ATTRIBUTE_KEY" %><%--
  Created by IntelliJ IDEA.
  User: Grigor
  Date: 25-May-20
  Time: 11:58 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<% if (request.getAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY) != null) {
    String s = (String) request.getAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY);
    response.getWriter().write(s);
}
%>
<br>
<form method="post" action="/forget-password">
    username : <input type="text" name="username"><br/>
    <input type="submit" name="submit"><br>
</form>

</body>
</html>
