<%@ page import="am.basic.jdbcStart.model.User" %>
<%@ page import="am.basic.jdbcStart.model.Comment" %>
<%@ page import="java.util.List" %>
<%@ page import="am.basic.jdbcStart.service.CommentManager" %>
<%@ page import="static am.basic.jdbcStart.util.Constants.ParameterKeyByClient.PARAMETER_USER_ATTRIBUTE_KEY" %><%--
  Created by IntelliJ IDEA.
  User: Grigor
  Date: 12-Jun-20
  Time: 1:32 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<%
    CommentManager commentService = new CommentManager();
    User user = (User) session.getAttribute(PARAMETER_USER_ATTRIBUTE_KEY);
    List<Comment> comments = commentService.getByUserId(user.getId());
%>

<table border="solid 1px">


    <%
        for (Comment comment : comments) {
    %>
    <tr>
        <td><%=comment.getTitle()%>
        </td>
        <td><%=comment.getNote()%>
        </td>
        <td>
            <form method="post" action="/secure/delete-comment">
                <input type="hidden" name="id" value="<%=comment.getId()%>">
                <input type="submit" value="DELETE">
            </form>
        </td>
            <%
        }
    %>

</table>

<form method="post" action="/secure/add-comment">
    Comment : <input type="text" name="title">
    Description : <input type="text" name="note">
    <input type="submit" value="add">
</form>



</body>
</html>
