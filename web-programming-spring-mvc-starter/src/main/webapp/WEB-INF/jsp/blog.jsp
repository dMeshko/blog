<%--
  Created by IntelliJ IDEA.
  User: Darko
  Date: 3/2/2016
  Time: 8:28 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title></title>
</head>
<body>
<h1>${done}</h1>
${img1}, ${img2}
<h2>Blog title: ${blogTitle}</h2>
<h4>Categories: </h4>
<c:forEach items="${blogCategories}" var="cat">
  <p>${cat.name}</p>
</c:forEach>

<form action="./" method="POST" enctype="multipart/form-data">
  <input type="text" name = "title" placeholder="title" />
  <textarea name="content" placeholder="content"></textarea>
  <input type="file" name="file" multiple />
  <select name = "enabled">
    <option value="1">Visible</option>
    <option value="0">Hidden</option>
  </select>
  <select name="categories" multiple>
  <c:forEach items="${blogCategories}" var="cat">
    <option value="${cat.id}">${cat.name}</option>
  </c:forEach>
</select>
  <select name="tags" multiple>
    <c:forEach items="${blogTags}" var="tag">
      <option value="${tag.id}">${tag.name}</option>
    </c:forEach>
  </select>
  <input type="submit" value="Create" />
</form>
</body>
</html>
