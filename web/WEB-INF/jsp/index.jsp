<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE HTML PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN"
    "http://www.w3.org/TR/html4/loose.dtd">
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>

<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>Welcome to Spring Web MVC project</title>
    </head>

    <body>
        <h6>Please select a list to view </h6>
        <form action="list" method="get">
            <input list="lists">
            <datalist id="lists">
                <option value="list1">
                <option value="list2">
                <option value="list3">
            </datalist>
        </form>
        <c:forEach var="listOfItems" items="${listOfLists}">
        <ol>
            <c:forEach var="item" items="${listOfItems}">
            <li>${item}</li>
            </c:forEach>
        </ol>
        </c:forEach>
    </body>
</html>
