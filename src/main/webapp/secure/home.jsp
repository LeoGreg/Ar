<%@ page import="am.basic.jdbcStart.model.User" %>
<%@ page import="static am.basic.jdbcStart.util.Constants.ParameterKeyByClient.PARAMETER_MESSAGE_ATTRIBUTE_KEY" %>
<%@ page import="static am.basic.jdbcStart.util.Constants.ParameterKeyByClient.PARAMETER_USER_ATTRIBUTE_KEY" %>
<%@ page import="static am.basic.jdbcStart.util.Constants.Message.SESSION_EXPIRED_MESSAGE" %>
<%@ page import="static am.basic.jdbcStart.util.Constants.PagesLinks.INDEX_PAGE" %>
<%@ page import="static am.basic.jdbcStart.util.Constants.PagesLinks.HOME_PAGE" %>
<%@ page import="am.basic.jdbcStart.model.Note" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>main page </title>
</head>
<body>
​
<h1 style="background: green" align="center">you are logged in </h1>
<a href="/logout" style="alignment: right">logout</a>
<%
    User user = (User) session.getAttribute(PARAMETER_USER_ATTRIBUTE_KEY);
    response.getWriter().write("Hello dear " + user.getName() + " " + user.getSurname());
%>

<br><br>
<% if (request.getAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY) != null) {
    String s = (String) request.getAttribute(PARAMETER_MESSAGE_ATTRIBUTE_KEY);

    response.getWriter().write(s);
}
%>


<br><br>

<ul>
    <li><a href="../changePassword.jsp">change_password</a>
</ul>


<br>
<form method="post" action="/note">
    title: <input type="text" name="title"><br>
    type here: <input type="text" name="text" placeholder="My Cool Placeholder Text">
    <input type="submit" name="submit" ><br>
</form>

<br>



<form method="post" action="/note">
    read notes:<input type="checkbox" name="click"><br>
    submit:<input type="submit" name="submit" >
</form>

<br>


<form method="post" action="/note">
     remove Notes:<input type="checkbox" name="clickR"><br>
    submit:<input type="submit" name="submit" >
</form>

</body>
</html>