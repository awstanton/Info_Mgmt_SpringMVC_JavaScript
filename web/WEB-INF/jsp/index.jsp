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
        <form action="simplelist" method="get">
            <p> Please select a list to view </p>
            <c:forEach var="list" items="${listOfLists}">
                <li>${list}</li>
            </c:forEach>
        </form>

        <h6>Add a new list</h6>
        <h6>Delete a list</h6>
    </body>
</html>


        <!--
            <!--c:forEach var="listOfItems" items="${listOfLists}">
            <ol>
                <!--c:forEach var="item" items="${listOfItems}">
                    <li>${item}</li>
                <--/c:forEach>
            </ol>
            <--/c:forEach>
        -->