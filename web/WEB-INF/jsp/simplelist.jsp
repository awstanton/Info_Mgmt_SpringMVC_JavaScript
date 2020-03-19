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
        <div id="VIEWMODE"> <!-- List of Items -->
            <h3>${currentList.name}</h3>
            <c:forEach var="item" items="${listOfItems}">
                <li>
                    <!-- Interactive List Name -->
                    <a id="${item.itemid}" contentEditable="false" href="${contextPath}/showitem/${item.itemid}">${item}</a>
                    <!-- Delete List -->
                    <form class="inlineblock" type="hidden" method="post" action="${contextPath}/delitem/${currentList.listid}/${item.itemid}">
                        <input id="delbtn${item.itemid}" class="red" type="hidden" value="-"/>
                    </form>
                </li>
            </c:forEach>
        </div>
		<div id="EDITMODE" class="editMode" >
			<div class="editColumn">
				<span>Edit Attribute Set</span><br>
				<c:forEach var="field" items="${currentList.outline}" varStatus="counter">
					<input id="editField${counter.count}" type="text" contentEditable="true" value="${field}"/><br>
				</c:forEach>
				<span>Add New Field</span><br><input id="addField"/>
			</div>
			<div class="editColumn">
				<span>Edit Item Names</span><br>
				<c:forEach var="item" items="${listOfItems}" varStatus="counter">
					<input id="editName${item.itemid}" type="text" contentEditable="true" value="${item}"/>
                                        <input id="deleteName${item.itemid}" type="submit" class="red" value="-"/><br>
				</c:forEach><br>
				<span>Add New Item</span><br><input id="addItem"/>
			</div>
<!--			<div class="editColumn">                                                        -->
<!--				<span>Delete Items</span>                                                   -->
<!--				<br><c:forEach var="item" items="${listOfItems}" varStatus="counter">       -->
<!--				<span style="color:red">${item}-</span>                                     -->
<!--				</c:forEach>                                                                -->
<!--			</div>                                                                          -->
			<div class="editColumn">
			<span>Editing ${currentList.name}</span>
			</div>
		</div>
    </body>
</html>

