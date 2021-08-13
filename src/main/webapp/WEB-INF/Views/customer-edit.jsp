<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Edit the entry</title>
    <link rel="stylesheet" href="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/css/bootstrap.min.css">
</head>
<body>
<div class="container">
  <h1>Edit the entry</h1>
  <form method="POST" action="update-customer">
    <input name="cusNo" type="hidden" value="${customer.getCustomerNumber()}" />
    <fieldset class="form-group">
      <label>Customer Name</label>
      <input type="text" name="customerName" class="form-control" value="${customer.getCustomerName()}" required>
    </fieldset>
    <fieldset class="form-group">
      <label>Customer Initial Deposit</label>
      <input type="text" name="customerInitialDeposit" class="form-control" value="${customer.getCustomerInitialDeposit()}" required>
    </fieldset>
    <fieldset class="form-group">
      <label>Number of Years</label>
      <input type="text" name="numberYears" class="form-control" value="${customer.getNumberOfYears()}" required>
    </fieldset>
    <fieldset class="form-group">
      <label>Savings type</label>
      <select name="savingsType" id="">
        <option value="savings-deluxe" ${customer.getSavingsType() == "savings-deluxe" ? 'selected="selected"': ''}>Savings deluxe</option>
        <option value="savings-regular" ${customer.getSavingsType() == "savings-regular" ? 'selected="selected"': ''}>Savings regular</option>
      </select>
    </fieldset>
    <input class="btn btn-success" type="submit" value="Submit" />
  </form>
</div>
<script src="https://ajax.googleapis.com/ajax/libs/jquery/3.5.1/jquery.min.js"></script>
<script src="https://maxcdn.bootstrapcdn.com/bootstrap/3.4.1/js/bootstrap.min.js"></script>
</body>
</html>
