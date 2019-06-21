<%--
  Created by IntelliJ IDEA.
  User: Евгений
  Date: 12.06.2019
  Time: 12:50
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <link rel="stylesheet" href="style.css">
    <title>Login</title>
</head>
<body>



<form class="box" action="login" method="post">



    <h1>Login</h1>

       <h1 style="color: lawngreen">${message}</h1>

    <input type="text" name="username" placeholder="Username">
    <input type="password" name="password" placeholder="Password">
    <input type="submit" name="submit" value="login"> <a class="linkreg" href="register" > Registration   </a>

</form>

</body>
</html>
