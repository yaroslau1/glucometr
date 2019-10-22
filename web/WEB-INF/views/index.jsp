<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form" %>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: USER-1
  Date: 17.09.2019
  Time: 14:16
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
</head>
<body>

<form role="form" method="POST" action="hello">
    <label for="user">Enter your name: </label>
    <input name="name" type="text" id="user" />
    <button type="submit">Submit</button>
</form>
<br/>

<h2>${message}</h2>



<form role="form" action="/connectToComPort" method="post">
    Select a Category:&nbsp;
    <select name="category">
        <c:forEach items="${comPortList}" var="category">
            <option value="${category}">${category}</option>
        </c:forEach>
    </select>
    <br/><br/>
    <button type="submit">Connect</button>
</form>

<form role="form" action="/refreshComPortNames" method="POST">
    <button type="submit">Refresh</button>
</form>

<h2>${messageComPort}</h2>


</body>
</html>