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
<h1>Savings Investment Record</h1>
<p>The following are the savings records.</p>
<h1 id="mes" onmouseout="myFunction()">${errorMessage}</h1>
<div class="container">
    <div class="container">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Customer number</th>
                    <th>Customer name</th>
                    <th>Customer deposit</th>
                    <th>Number of years</th>
                    <th>Savings type</th>
                    <th colspan="3"></th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${customers}" var="customer">
                    <tr>
                        <td>${customer.customerNumber}</td>
                        <td>${customer.customerName}</td>
                        <td>${customer.customerInitialDeposit}</td>
                        <td>${customer.numberOfYears}</td>
                        <td>${customer.savingsType}</td>
                        <td><a type="button" class="btn btn-primary"
                               href="update-customer?id=${customer.customerNumber}">Edit</a></td>
                        <td><a type="button" class="btn btn-primary"
                               href="delete-customer?id=${customer.customerNumber}" onclick="return confirm('Are you sure you want to delete this customer?');">Delete</a></td>
                        <td><a type="button" class="btn btn-primary"
                               href="projected-investment-customer?id=${customer.customerNumber}">Projected Investment</a></td>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a class="btn btn-success" href="add-customer">Add</a>
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
