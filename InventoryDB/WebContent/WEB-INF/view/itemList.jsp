<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core" %>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags" %>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inventory Application</title>
<style>
	h1 {text-align:center;}
	th {border: solid 1px darkgrey;}
	.data td{
		text-align:center;
		border: solid 1px darkgrey;
	}
	#action {border: none;}
	a.button {
    -webkit-appearance: button;
    -moz-appearance: button;
    appearance: button;
	font-family:sans-serif;
	font-size:0.8em;
    text-decoration: none;
    color: initial;
    padding:4px 10px 4px 10px;
	}
	.container {
		display:block;
		margin:auto;
		width:415px;
		text-align:center;
	}
	.search {display:inline-block;"}
</style>
</head>
<body>
<spring:url var="updateUrl" value="/item/process" />
	<h1>
		<spring:message code="application.name" />
	</h1>
<form:form action="${updateUrl}" method="post" modelAttribute="item">
<div class="container">
	<table border="2" class="data">
		<tr>
			<th><spring:message code="item.id" /></th>
			<th><spring:message code="item.name" /></th>
			<th><spring:message code="item.price" /></th>
			<th><spring:message code="item.stock" /></th>
			<th>Action</th>
		</tr>
		<c:forEach items="${items}" var="item">
			<tr>
				<td>${item.stockID}</td>
				<td>${item.itemName}</td>
				<td>${item.unitPrice}</td>
				<td>${item.onStock}</td>
				<td id="action">
					<a class="button" href="process/${item.stockID}/edit">Edit</a>
					<a class="button" href="process/${item.stockID}/delete" 
					onclick="return confirm('Are you sure you want to delete ${item.itemName}?')">Delete</a>
				</td>	
			</tr>
		</c:forEach>
	</table>
	<br/>
	<spring:url value="/item/add.html" var="addUrl"/>
	<spring:url value="/item/add.html?button=search" var="searchUrl"/>
	<input type="button" value=" Add Item " onclick="location.href='${addUrl}'" class="search"/>
	<input type="button" value=" Search " onclick="location.href='${searchUrl}'" class="search"/>
	<br/>
</div>
</form:form>
</body>
</html>