<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit the entry</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <h1>Edit the entry</h1>
  <form method="POST" action="update-category">
    <input name="catCode" type="hidden" value="${category.getCatCode()}" />
    <fieldset class="form-group">
      <label>Category description</label>
      <input name="catDesc" type="text" class="form-control" required value="${category.getCatDesc()}" />
    </fieldset>
    <input class="btn btn-success" type="submit" value="Submit" />
  </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</body>
</html>
