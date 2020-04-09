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
        <div id="viewListSection" class="viewItemList">
            <h4><b>${currentList.name}</b></h4>
            <c:forEach var="item" items="${listOfItems}">
                <li>
                    <a id="view${item.itemid}" value="${item}" contentEditable="false" spellcheck="false" href="${contextPath}/showitem/${item.itemid}" class="item">${item}</a>
                </li>
            </c:forEach>
        </div>
        <div id="editListSection" class="editItemList flex displayNone">
            <h4 class="heading">Items</h4>
            
            <c:forEach var="item" items="${listOfItems}">
                <li>
                    <input id="${item.itemid}" value="${item}" type="text" contentEditable="true" maxlength="50" spellcheck="false" href="${contextPath}/showitem/${item.itemid}" class="item"/><!--
                 --><input id="delitm${item.itemid}" value="X" type="button" class="itmbtn red"/>
                </li>
            </c:forEach>
            
            <div id="addItemSection">
                <input id="addItm" placeholder="Add New Item" maxlength="50" type="text" class="item"/><!--
             --><input id="addItmBtn" value="O" type="button" class="itmbtn green"/>
            </div>
        </div>
        
        <div id="sidebarSection" class="sidebar flex displayNone">
            <h4 class="heading">Fields</h4>
            
            <c:forEach var="field" items="${currentList.outline}" varStatus="counter">
                <div>
                    <input id="field${counter.count}" value="${field}" type="text" maxlength="50" contentEditable="true" spellcheck="false" class="fld"/><!--
                 --><input id="delfld${counter.count}" value="X" type="button" class="fldbtn red"/>
                </div>
            </c:forEach>
            <div id="addFieldSection">
                <input id="addFld" placeholder="Add New Field" maxlength="50" type="text"/><!--
             --><input id="addFldBtn" value="O" type="button" class="fldbtn green"/><br>
            </div>
            
        </div>
            
        <div id="submitSection" class="displayNone">
            <h4 id="editHeader" class="heading">Editing <b>${currentList.name}</b></h4>
            <!--<label>Remove Duplicates<input id="duplicatesCheckbox" type="checkbox"/></label><br><br>-->
            <input id="submitbutton" type="button" value="Submit"/>
            <!--<p id="dupMsg" class="displayNone">Duplicates found (highlighted). If you want to continue, click Submit again. Otherwise, remove duplicates and then click Submit. </p>-->
            <form id="submitform" method="post" action="/SimpleSpringMVC/updateList/${currentList.listid}" class="displayNone"></form>
        </div>
    </body>
    <script>var nextItemId = <c:out value="${nextItemId}"/></script>
    <script src="${contextPath}/resources/js/simplelist.js"></script>
</html>

