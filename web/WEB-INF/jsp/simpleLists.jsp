<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
    </head>

    <body>
        <div>
            <p>Your Lists</p>
            <c:forEach var="list" items="${listOfLists}">
                <li><a href="${contextPath}/showlist/${list.listid}">${list}</a></li>
            </c:forEach>
        </div>
        <div>
            <!--<form action="addlist">
                <br><a href="${contextPath}/addlist">Create New List</a>
                <input type="text" name="name" required>
                <input type="submit" value="Submit">
            </form>-->
            <p>Create New List</p>
            <form:form action="addlist/${listId}" method="post" modelAttribute="list">
                <form:input path="name" />
                <input type="submit" value="Submit"/>
            </form:form>
        </div>
    </body>
</html>
