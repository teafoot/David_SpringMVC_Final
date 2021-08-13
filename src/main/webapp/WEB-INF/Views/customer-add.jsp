<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
  <title>Add an entry</title>
  <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <h1>Add an entry</h1>
  <form method="post" action="add-customer">
    <fieldset class="form-group">
      <label>Customer Number</label>
      <input type="text" name="cusNo" class="form-control" required>
    </fieldset>
    <fieldset class="form-group">
      <label>Customer Name</label>
      <input type="text" name="customerName" class="form-control" required>
    </fieldset>
    <fieldset class="form-group">
      <label>Customer Initial Deposit</label>
      <input type="text" name="customerInitialDeposit" class="form-control" required>
    </fieldset>
    <fieldset class="form-group">
      <label>Number of Years</label>
      <input type="text" name="numberYears" class="form-control" required>
    </fieldset>
    <fieldset class="form-group">
      <label>Savings type</label>
      <select name="savingsType" id="">
        <option value="savings-deluxe">Savings deluxe</option>
        <option value="savings-regular">Savings regular</option>
      </select>
    </fieldset>
    <br>
    <input class="btn btn-success" type="submit" value="Submit">
  </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</body>
</html>
