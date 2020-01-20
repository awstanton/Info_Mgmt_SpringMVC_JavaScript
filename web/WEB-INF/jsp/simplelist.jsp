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
    </head>
    
    <body>
        <div>
            <h3>${currentList.name}</h3>
            <c:forEach var="item" items="${listOfItems}">
                <li><a href="${contextPath}/showitem/${item.itemid}" target="_self">${item}</a></li>
            </c:forEach>
        </div>
        <div>
            <h6>Add New Item</h6>
            <form:form action="${contextPath}/additem/${currentList.listid}" method="post" modelAttribute="newItem">
                <form:input path="name"/>
                <input type="submit" value="Submit"/>
            </form:form>
            <c:if test="${not empty emptyField}">${emptyField}</c:if>
        </div>
        <!--<script src="${contextPath}/resources/js/events.js"></script>-->
    </body>
</html>

