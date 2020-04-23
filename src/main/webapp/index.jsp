<%@ page language="java" contentType="text/html; charset=UTF-8"
    pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html>
<head>
<meta charset="UTF-8">
<title>Home</title>
</head>
<body>
 <a href="/spring-mvc-java-config/employees">Show Employee List</a>
<form action="/spring-mvc-java-config/addNew" method="post" modelAttribute="emp">
<input type="text" name="empid" placeholder="Enter the ID"/> <br>
<input type="text" name="empname" placeholder="Enter the Name" /> <br>
<input type="text" name="gender" placeholder="Enter the Gender" /> <br>
<input type="text" name="design" placeholder="Enter the Design" /> <br>
<input type="text" name="city" placeholder="Enter the City" /> <br>
<input type="text" name="salary" placeholder="Enter the Salary" /> <br>
<input type="submit" value="Add Employee" />
</form>
<!-- form for adding account details -->

<form action="/spring-mvc-java-config/addNewAccount" method="post" modelAttribute="account">
<input type="text" name="id" placeholder="Enter the ID"/> <br>
<input type="text" name="type" placeholder="Enter the Type" /> <br>
<input type="submit" value="Add Account" />
</form>

</body>
</html>