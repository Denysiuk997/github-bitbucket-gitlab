<%--
  Created by IntelliJ IDEA.
  User: Евгений
  Date: 12.06.2019
  Time: 13:14
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Registration</title>
    <link rel="stylesheet" href="style.css">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<jsp:include page="/headerMenu.html" />"


<form class="reqResp" method="post">
 <h1>Request for task!</h1>

    <h2 style="color: lawngreen">${successmessage}</h2> <h2 style="color: lawngreen">${errormessage}</h2>

     <input type="text" name="taskname" placeholder="Task name:">
    <textarea  name="description" placeholder="Description:"></textarea>
     <input type="submit" name="submit" value="request">


</form>
</body>
</html>
