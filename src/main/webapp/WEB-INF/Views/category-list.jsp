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
<h1>Categories</h1>
<h1 id="mes" onmouseout="myFunction()">${errorMessage}</h1>
<div class="container">
    <div class="container">
        <table class="table table-striped">
            <thead>
                <tr>
                    <th>Category Code</th>
                    <th>Category Description</th>
                    <th colspan="2">Action</th>
                    <th>View Category Items</th>
                </tr>
            </thead>
            <tbody>
                <c:forEach items="${categories}" var="category">
                    <tr>
                        <td>${category.catCode}</td>
                        <td>${category.catDesc}</td>
                        <td><a type="button" class="btn btn-primary"
                               href="update-category?id=${category.catCode}">Edit</a></td>
                        <td><a type="button" class="btn btn-primary"
                               href="delete-category?id=${category.catCode}">Delete</a></td>
                        <td><a type="button" class="btn btn-primary"
                               href="view-category-items?id=${category.catCode}">View Items</a></td>
<%--                        <c:if test="${category.getItems().size() > 0}">&lt;%&ndash; if there are items for this category, create combobox with items &ndash;%&gt;--%>
<%--                            <td>--%>
<%--                                <select name="" id="">--%>
<%--                                <c:forEach items="${category.getItems()}" var="item">--%>
<%--                                    <option value="${item.getItemCode()}">${item.getItemDesc()}</option>--%>
<%--                                </c:forEach>--%>
<%--                                </select>--%>
<%--                            </td>--%>
<%--                        </c:if>--%>
                        <c:choose>
                            <c:when test="${category.getItems().size() > 0}"><%-- if there are items for this category, create combobox with items --%>
                                <td>
                                    <select name="" id="">
                                    <c:forEach items="${category.getItems()}" var="item">
                                        <option value="${item.getItemCode()}">${item.getItemDesc()}</option>
                                    </c:forEach>
                                    </select>
                                </td>
                            </c:when>
                            <c:otherwise>
                                <td>No items found.</td>
                            </c:otherwise>
                        </c:choose>
                    </tr>
                </c:forEach>
            </tbody>
        </table>
        <a class="btn btn-success" href="add-category">Add New Category</a>
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
