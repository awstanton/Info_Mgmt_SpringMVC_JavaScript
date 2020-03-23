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
        <div class="mainItemList">
            <!--<h1>${currentList.name}</h1>-->
            <h1>Items</h1>
            
            
            <c:forEach var="item" items="${listOfItems}">
                <li>
                    <a id="${item.itemid}" contentEditable="false" href="${contextPath}/showitem/${item.itemid}">${item}</a>
                    <input id="delbtn${item.itemid}" value="X" type="submit" class="delbtn"/>
                </li>
            </c:forEach>
            
                <div><input id="addItem" placeholder="Add New Item"/></div><br>
        </div>
            
        <div class="sidebar">
            <h4>Fields</h4>
            
            <c:forEach var="field" items="${currentList.outline}" varStatus="counter">
                <div>
                    <input id="editField${counter.count}" value="${field}" type="text" maxlength="30" contentEditable="true" class="fld"/>
                    <input id="delfld${item.itemid}" value="X" type="submit" class="delfld"/><br>
                </div>
            </c:forEach>
            
            <input id="addField" placeholder="Add New Field" maxlength="30"/><br>
        </div>
            
        <div id="editMode">
            <h4>Editing <b>${currentList.name}</b></h4>
        </div>
    </body>
</html>

