<%-- Created by IntelliJ IDEA.
User: cjjc2 Date: 2021-07-09 Time: 1:56 a.m. To change this template use File | Settings | File Templates. --%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
    <script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
    <title>Items</title></head>
<body>
<div class="container"><h1>Items available under the Category Code: ${catCode}</h1>
    <table class="table table-striped">
        <thead>
        <tr>
            <th>Item Code</th>
            <th>Item Description</th>
        </tr>
        </thead>
        <tbody>
            <c:forEach items="${items}" var="item">
                <tr>
                    <td>${item.getItemCode()}</td>
                    <td>${item.getItemDesc()}</td>
                </tr>
            </c:forEach>
        </tbody>
    </table>
    <a type="button" class="btn btn-primary" href="list-categories">Go back</a>
</div>
</body>
</html>