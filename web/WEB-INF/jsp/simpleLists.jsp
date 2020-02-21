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
        <!--<script src="http://ajax.microsoft.com/ajax/jquery/jquery-1.4.2.min.js"></script>-->
    </head>

    <body>
        <div> <!-- List of Lists -->
            <h2>Your Lists</h2>
            <c:forEach var="list" items="${listOfLists}">
                <li>
                    <!-- Interactive List Name -->
                    <a id="${list.listid}" contentEditable="false" href="${contextPath}/showlist/${list.listid}" target="_self">${list}</a>
                    <!-- Delete List --> <!-- Note: id only used in commented out part of javascript -->
                    <form id="delform" type="hidden" method="post" action="dellist/${list.listid}" class="inlineblock">
                        <input id="delbtn${list.listid}" type="hidden" class="red" value="-"/>
                    </form>
                </li>
            </c:forEach>
        </div>
        <div> <!-- Add List -->
            <form:form action="addlist/${listId}" method="post" modelAttribute="list">
                <form:errors path="name"/>
                <form:input id="addbox" path="name" type="hidden" autocomplete="off"/>
                <input id="addbtn" type="hidden" value="+" class="green"/><br>
            </form:form>
            <c:choose>
                <c:when test="${not empty emptyField}">${emptyField}</c:when>
                <c:when test="${not empty tooLong}">${tooLong}</c:when>
                <c:when test="${not empty duplicateField}">${duplicateField}</c:when>
            </c:choose>
        </div>
        <div> <!-- Update List Names Form; JS script -->
            <form id="updateform" type="hidden" method="post" action="/SimpleSpringMVC/updateListNames"></form>
            <script src="${contextPath}/resources/js/events.js"></script>
        </div>
            
    </body>
</html>
