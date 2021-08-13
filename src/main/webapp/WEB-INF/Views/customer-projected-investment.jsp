<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Category Page</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
    <style>
        h1 {
            text-align: center;
            background-color: cyan;
        }
        .btn {
            width: 100%;
        }
    </style>
</head>
<body>
<h1>Projected Table of Customer Number: ${customer.getCustomerNumber()}</h1>
<h1>Customer Name: ${customer.getCustomerName()}</h1>
<h1 id="mes" onmouseout="myFunction()">${errorMessage}</h1>
<div class="container">
    <div class="container">
        <table class="table table-striped">
            <tbody>
                <c:if test="${customer != null}"><%-- if there are items for this category, create combobox with items --%>
                    <th>Year</th>
                    <c:forEach items="${year}" var="ye">
                        <tr>
                        <td><c:out value="${ye}"/></td>
                        </tr>
                    </c:forEach>
                    <th>Starting amount</th>
                    <c:forEach items="${starting}" var="start">
                        <tr>
                        <td><c:out value="${start}"/></td>
                        </tr>
                    </c:forEach>
                    <th>Interest</th>
                    <c:forEach items="${interest}" var="inte">
                        <tr>
                        <td><c:out value="${inte}"/></td>
                        </tr>
                    </c:forEach>
                    <th>Ending Balance</th>
                    <c:forEach items="${ending}" var="end">
                        <tr>
                        <td><c:out value="${end}"/></td>
                        </tr>
                    </c:forEach>
                </c:if>
            </tbody>
        </table>
    </div>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
<script>
    function myFunction() {
        document.getElementById("mes").innerHTML = "";
    }
</script>
</body>
</html>
