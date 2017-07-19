<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inventory Application</title>
<style>
	h1 {text-align:center;}
	.errors {color:red;	font-weight:bold;}
	.readonly {	background-color:lightgrey;	}
	.container {
		display:block;
		margin:auto;
		width: 300px;
		border:2px solid grey;
		padding-top:20px;
		background-color:#f2f2f2;
		text-align:center;
	}
	.spacer {padding-bottom:10px;}
	.details {display:inline-block; margin-bottom:20px;}
</style>
<script>
	function cancel() {
		document.getElementById("name").removeAttribute("required");
		document.getElementById("price").removeAttribute("required");
		document.getElementById("stock").removeAttribute("required");
	}
</script>
</head>
<body>
<spring:url var="updateUrl" value="/item/process" />
	<h1>
		<spring:message code="application.name"/>
	</h1>
<form:form action="${updateUrl}" method="post" modelAttribute="item">
<div class="container">
	<table width="300">
		<tr>
			<th colspan=2><spring:message code="item.details" /></th>
		</tr>
		<tr><td class="spacer"> </td></tr>
		<tr>
			<td><form:label path="stockID"></form:label><spring:message code="item.id" /></td>
			<td><form:input path="stockID" disabled="true" class="readonly"/></td>
		</tr><tr>
			<td colspan=2><form:errors path="stockID" class="errors"/></td>
		</tr>
		<tr>
			<td><form:label path="itemName"></form:label><spring:message code="item.name" /></td>
			<td><form:input path="itemName" required="true" id="name"/></td>
		</tr><tr>
			<td colspan=2><form:errors path="itemName" class="errors"/></td>
		</tr>
		<tr>
			<td><form:label path="unitPrice"></form:label><spring:message code="item.price" /></td>
			<td><form:input path="unitPrice" required="true" id="price"/> </td>
		</tr><tr>
			<td colspan=2><form:errors path="unitPrice" class="errors"/></td>
		</tr>
		<tr>
			<td><form:label path="onStock"></form:label><spring:message code="item.stock" /></td>
			<td><form:input path="onStock" required="true" id="stock"/></td>
		</tr><tr>
			<td colspan=2><form:errors path="onStock" class="errors"/></td>
		</tr>
		<tr><td class="spacer"> </td></tr>
	</table>
		<spring:url value="/item/process/update" var="changeUrl"/>
		<input type="submit" value="Update" name="update" class="details"/>
		<input type="submit" value="Cancel" name="list" onclick="cancel();" class="details"/>
</div>	
</form:form>
</body>
</html>