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
        <div> <!-- List of Items -->
            <h3>${currentList.name}</h3>
            <c:forEach var="item" items="${listOfItems}">
                <li>
                    <!-- Interactive List Name -->
                    <a id="${item.itemid}" contentEditable="false" href="${contextPath}/showitem/${item.itemid}" target="_self">${item}</a>
                    <!-- Delete List -->
                    <form type="hidden" method="post" action="${contextPath}/delitem/${currentList.listid}/${item.itemid}" class="inlineblock">
                        <input id="delbtn${item.itemid}" type="hidden" class="red" value="-"/>
                    </form>
                </li>
            </c:forEach>
        </div>
        <div> <!-- Add Item -->
            <form:form action="${contextPath}/additem/${currentList.listid}" method="post" modelAttribute="newItem">
                <form:input id="addbox" path="name" type="hidden" autocomplete="off"/>
                <input id="addbtn" type="hidden" value="+" class="green"/>
            </form:form>
            <c:choose>
                <c:when test="${not empty emptyField}">${emptyField}</c:when>
                <c:when test="${not empty tooLong}">${tooLong}</c:when>
                <c:when test="${not empty duplicateField}">${duplicateField}</c:when>
            </c:choose>
        </div>
            <div style="border:1px solid black;width:200px">
<!--                <ol>-->
                    <c:forEach var="field" items="${currentList.outline}" varStatus="counter">
                        <input id="field${counter.count}" type="text" contentEditable="false" style="display:none;width:100px;text-align:left;" value="${field}"/><br>
                    </c:forEach>
<!--                </ol>-->
            </div>
        <div> <!-- Update Item Names Form; JS script -->
            <form id="updateform" type="hidden" method="post" action="/SimpleSpringMVC/updateItemNames/${currentList.listid}"></form>
            <script src="${contextPath}/resources/js/events.js"></script>
        </div>
    </body>
</html>

