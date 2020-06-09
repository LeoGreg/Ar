<%@ page import="am.basic.jdbcStart.model.Note" %>
<%@ page import="java.util.ArrayList" %>
<%@ page import="static am.basic.jdbcStart.util.Constants.ParameterKeyByClient.NOTE_GET_KEY" %><%--
  Created by IntelliJ IDEA.
  User: Grigor
  Date: 07-Jun-20
  Time: 6:31 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>
<%

    ArrayList<Note> note = (ArrayList<Note>) request.getAttribute(NOTE_GET_KEY);
    response.getWriter().write("\n"+
            note.toString()+" "+
            "\n");
%>

</body>
</html>
