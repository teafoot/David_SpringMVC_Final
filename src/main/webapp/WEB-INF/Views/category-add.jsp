<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add an entry</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <h1>Add an entry</h1>
  <form method="post" action="add-category">
    <fieldset class="form-group">
      <label>Category code</label>
      <input type="text" name="catCode" class="form-control" required>
    </fieldset>
    <fieldset>
      <label>Category description</label>
      <input type="text" name="catDesc" class="form-control" required>
    </fieldset>
    <br>
    <input class="btn btn-success" type="submit" value="Submit">
  </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</body>
</html>
