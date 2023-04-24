<%@ page contentType="text/html; charset=UTF-8" pageEncoding="UTF-8" %>
<!DOCTYPE html>
<html>
<head>
    <title>JSP - Hello World</title>
</head>
<body>
<form method="post" action="<%=request.getContextPath()%>/posts-servlet">
    <div>
        <label>Title: </label>
        <input type="text" name="title" placeholder="title">
    </div>
    <div>
        <label>Meta Title: </label>
        <input type="text" name="Mtitle" placeholder="Meta Title">
    </div>
    <div>
        <label>Category: </label>
        <input type="text" name="category" placeholder="category">
    </div>
    <div>
        <label>Content: </label>
        <textarea name="content" cols="30" rows="10"></textarea>
    </div>
    <div>
        <label>Author: </label>
        <input type="text" name="author" placeholder="author">
    </div>
    <button type="submit"> Save </button>
</form>
</body>
</html>