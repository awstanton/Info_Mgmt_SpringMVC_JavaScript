<%@page contentType="text/html" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
    <head>
        <meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
        <title>JSP Page</title>
    </head>
    <body>
        <h6>Please select an item to view </h6>
        <form action="item" method="get">
            <input list="items">
            <datalist id="items">
                <option value="item1">
                <option value="item2">
                <option value="item3">
            </datalist>
        </form>
    </body>
</html>
