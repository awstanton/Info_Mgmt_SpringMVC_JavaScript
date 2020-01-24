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
                    <a id="${item.itemid}" contentEditable="false" href="${contextPath}/showitem/${item.itemid}" target="_self">${item}</a>
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
            <c:if test="${not empty emptyField}">${emptyField}</c:if><br>
        </div>
        <div> <!-- Update Item Names Form; JS script -->
            <form id="updateform" type="hidden" method="post" action="/SimpleSpringMVC/updateItemNames/${currentList.listid}"></form>
            <script src="${contextPath}/resources/js/events.js"></script>
        </div>
    </body>
</html>

