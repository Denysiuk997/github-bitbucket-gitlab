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
    <link rel="stylesheet" href="style.css">
    <title>Registration</title>
</head>
<body>


<form class="box" method="post">
    <h1>Register Page!</h1>
    <h1 style="color: lawngreen">${message}</h1> <h1 style="color: lawngreen"> ${errormessage}</h1>
        <input type="text" name="userName" placeholder="UserName:">
      <input type="text" name="name"placeholder="Name:">
      <input type="password" name="password1"placeholder="Password:">
       <input type="password" name="password2"placeholder="Password:">
      <input type="submit" name="submit" value="register">


</form>
</body>
</html>
