<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <title>Title</title>
</head>
<body>
<% if (request.getAttribute("message") != null) {
String s = (String) request.getAttribute("message");
response.getWriter().write(s);
}
%>
<form method="post" action="/ch">
    <label for="fname">oldPassword:</label><br>
    <input type="text" id="fname" name="password"><br>
    <label for="surn">newPassword</label><br>
    <input type="text" id="surn" name="newPassword"><br>
    <label for="user">confirmPassword</label><br>
    <input type="text" id="user" name="confirmPassword"><br>
    <input type="submit" name="change">
</form>
</body>
</html>