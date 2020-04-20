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
    </head>
    
    <body style="text-align: center; background-color: azure">
        <div>
            <h3 style="font-size: 2em; margin: 16px">${currentItem.name}</h3><br>
        </div>
        <h6 style="margin-bottom: 8px; margin-top: 0px; font-size: 1em">Content</h6>
        <div style="margin-bottom: 16px">
            <textarea maxlength="2000" cols="100" rows="15" style="resize: none; cursor: default; padding: 5px" readonly>This is a sample. Second line. And a long stupid paragraph about nothing in particular.</textarea>
        </div>
        <h6 style="margin-bottom: 8px; margin-top: 0px; font-size: 1em">Second Field</h6>
        <div style="margin-bottom: 16px">
            <textarea maxlength="2000" cols="100" rows="15" style="resize: none; cursor: default; padding: 5px" readonly>This is a sample. Second line. And a long stupid paragraph about nothing in particular.</textarea>
        </div>
    </body>
</html>


<!--Ranking:&nbsp<p>${currentItem.ranking}</p>-->