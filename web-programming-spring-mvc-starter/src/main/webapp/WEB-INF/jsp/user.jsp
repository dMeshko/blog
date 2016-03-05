<%--
  Created by IntelliJ IDEA.
  User: Darko
  Date: 3/5/2016
  Time: 1:46 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title></title>
</head>
<body>

<form action="./signup" method="POST" enctype="multipart/form-data">
  <input name="name" placeholder="name" /><br />
  <input name="surname" placeholder="surname" /><br />
  <input name="email" placeholder="email" /><br />
  <input name="username" placeholder="username" /><br />
  <input name="password" placeholder="password" /><br />
  <input type="file" name="file" placeholder="file" /><br />
  <input type="submit" value="Create" />
</form>


</body>
</html>
