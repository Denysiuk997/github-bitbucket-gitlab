<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%--
  Created by IntelliJ IDEA.
  User: Евгений
  Date: 12.06.2019
  Time: 21:33
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Title</title>
    <link rel="stylesheet" href="styleProfile.css">
    <meta http-equiv="Content-Type" content="text/html; charset=utf-8">
</head>
<body>
<jsp:include page="/headerMenu.html" />

<div class="globalprofile">
<form class="buttonMenu" method="post">

      <input type="submit" name="submit" value="Active tasks">
          <input type="submit" name="submit" value="Received tasks">
          <input type="submit" name="submit" value="Request tasks">
         <input type="submit" name="submit" value="Finish tasks">
</form>
</div>

<div class="nameGroup">

    <c:choose>
        <c:when test="${messageChangeStatus==null}">
            ${messageGroupTask}
            <br />
        </c:when>
        <c:otherwise>
            ${messageChangeStatus}
            <br />
        </c:otherwise>
    </c:choose>



</div>




<%if (request.getAttribute("activeTasks") != null) {%>
<form class="buttonFC" action="profile" method="post">

    <div class="globalprofile">
        <c:forEach items="${activeTasks}" var="post">
            <div class="profile">
                <div class="container">
                    <div class="bubble">
                        <div class="rectangle"><h2>${post.getTaskName()}</h2></div>
                        <div class="triangle-l"></div>
                        <div class="triangle-r"></div>
                        <div class="info">
                            <h2>Start Date/Time:</h2>
                            <h2> ${post.getStartTime()}</h2>
                            <p>
                                    ${post.getDescription()}
                            </p>
                            <p>
                                <button value=${post.getIdTask()}F name="submit" type="submit">Finish Task</button>
                                <button value=${post.getIdTask()}C name="submit" type="submit">Cancel Task</button>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</form>
<% }%>













<%if (request.getAttribute("finishTasks") != null) {%>

<div class="globalprofile">
    <c:forEach items="${finishTasks}" var="post">
        <div class="profile">
            <div class="container">
                <div class="bubble">
                    <div class="rectangle"><h2>${post.getTaskName()}</h2></div>
                    <div class="triangle-l"></div>
                    <div class="triangle-r"></div>
                    <div class="info">
                        <h2> ${post.getStartTime()} </h2>
                        <h2> ${post.getEndTime()} </h2>
                        <p>
                                ${post.getDescription()}
                        </p>
                    </div>
                </div>
            </div>
        </div>
    </c:forEach>
</div>
</form>
<% }%>


<%if (request.getAttribute("reciveTasks") != null) {%>
<form class="buttonFC" action="profile" method="post">


    <div class="globalprofile">
        <c:forEach items="${reciveTasks}" var="post">
            <div class="profile">
                <div class="container">
                    <div class="bubble">
                        <div class="rectangle"><h2>${post.getTaskName()}</h2></div>
                        <div class="triangle-l"></div>
                        <div class="triangle-r"></div>
                        <div class="info">
                            <h2>Start Date/Time:</h2>
                            <h2> ${post.getStartTime()}</h2>
                            <p>
                                    ${post.getDescription()}
                            </p>
                            <p>
                                <button value=${post.getIdTask()}S name="submit" type="submit">Start Task</button>
                                <button value=${post.getIdTask()}V name="submit" type="submit">Cancel Task</button>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</form>
<% }%>


<%if (request.getAttribute("requestTasks") != null) {%>
<form class="buttonFC" action="profile" method="post">


    <div class="globalprofile">
        <c:forEach items="${requestTasks}" var="post">
            <div class="profile">
                <div class="container">
                    <div class="bubble">
                        <div class="rectangle"><h2>${post.getTaskName()}</h2></div>
                        <div class="triangle-l"></div>
                        <div class="triangle-r"></div>
                        <div class="info">
                            <h2>Start Date/Time:</h2>
                            <h2> ${post.getStartTime()}</h2>
                            <p>
                                    ${post.getDescription()}
                            </p>
                            <p>
                                <button value=${post.getIdTask()}B name="submit" type="submit">Cancel Task</button>
                            </p>
                        </div>
                    </div>
                </div>
            </div>
        </c:forEach>
    </div>
</form>
<% }%>


</body>
</html>
