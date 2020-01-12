<%@page contentType="text/html" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib uri="http://www.springframework.org/tags/form" prefix="form"%>
<%@ taglib uri="http://www.springframework.org/tags" prefix="spring"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01//EN"
    "http://www.w3.org/TR/html4/strict.dtd">

<c:set var="contextPath" value="${pageContext.request.contextPath}"/>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <link rel="stylesheet" type="text/css" href="${contextPath}/resources/css/stylesheet.css">
        <script src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.2.min.js"></script>
    </head>

    <body>
        <div>
            <h2>Your Lists</h2>
            <c:forEach var="list" items="${listOfLists}">
                <li><a href="${contextPath}/showlist/${list.listid}" target="_self">${list}</a></li>
            </c:forEach>
            
        </div>
        <div>
            <h6>Create New List</h6>
            <form:form action="addlist/${listId}" method="post" modelAttribute="list">
                <form:input path="name" />
                <input type="submit" value="Submit"/>
            </form:form>
            <c:if test="${not empty emptyField}">${emptyField}</c:if>
            <script src="${contextPath}/resources/js/events.js"></script>
        </div>
    </body>
</html>
