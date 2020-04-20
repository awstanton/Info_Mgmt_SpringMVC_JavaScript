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
        <! -- &vellip; -->
        <div id="lsttopbar" class="topbar">
            <input id="srchbox" class="srchbox" placeholder="Search"/>
            <a id="mnubtn" class="mnubtn" href="#">&equiv;</a>
        </div>
        <div id="lstlst" class="viewItemList">
            <h2>Your Lists</h2>
            <c:forEach var="list" items="${listOfLists}" varStatus="counter">
                <li style="margin-bottom: 10px">
                    <div class="lstnamwrp">
                        <a id="${list.listid}" class="lstnam" contentEditable="false" href="${contextPath}/showlist/${list.listid}">${list}</a><!--
                        --><form id="delform${counter.count}" class="lstdelfrm" method="post" action="dellist/${list.listid}">
                            <input id="delbtn${list.listid}" class="red lstbtn displayNone" type="submit" value="X">
                        </form>
                    </div>
                </li>
            </c:forEach>
                
            <li style="margin-bottom: 20px">
                <form:form id="listAddForm" class="lstaddfrm" action="addlist/${listId}" method="post" modelAttribute="list">
                    <form:errors path="name"/>
                    <form:input id="listAddBox" class="lstaddbox displayNone" placeholder="Add new list" path="name" type="input" maxlength="50" spellcheck="false" autocomplete="off" autocorrect="off" autocapitalize="off"/><!--
                    --><input id="listAddButton" class="green lstbtn displayNone" value="O" type="submit" /><br>
                </form:form>
                <c:choose>
                    <c:when test="${not empty emptyField}">${emptyField}</c:when>
                    <c:when test="${not empty tooLong}">${tooLong}</c:when>
                    <c:when test="${not empty duplicateField}">${duplicateField}</c:when>
                </c:choose>
            </li>
        </div>
        <div>
            <form id="updateListForm" class="displayNone" method="post" action="/SimpleSpringMVC/updateListNames"></form>
        </div>
            
    </body>
    <script>var nextListId = <c:out value="${nextListId}"/></script>
    <script src="${contextPath}/resources/js/simpleLists.js"></script>
</html>
