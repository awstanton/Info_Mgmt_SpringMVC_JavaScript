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
    
    <body>
        <div>
            <h3>${currentItem.name}</h3><br>
            Ranking:&nbsp<p>${currentItem.ranking}</p>
            Description:&nbsp<p>${currentItem.description}</p>
            <!--
            for each property, if it is not hidden, display its name and value
            
            
            
            -->
        </div>
    </body>
</html>
