<%@ page language="java" contentType="text/html; charset=ISO-8859-1"
    pageEncoding="ISO-8859-1"%>
<%@ taglib prefix="spring" uri="http://www.springframework.org/tags"%>
<%@ taglib prefix="form" uri="http://www.springframework.org/tags/form"%>
<%@ taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<title>Inventory Application</title>
<style>
	h1 {text-align:center;}
	.header {font-size: 1.2em;}
	.spacer {padding-bottom:25px;}
	.errors {color:red;	font-weight:bold; margin-top:5px; margin-bottom:5px;}
	.container {
		display:block;
		margin:auto;
		width: 300px;
		border:2px solid grey;
		padding-top:20px;
		background-color:#f2f2f2;
		text-align:center;
	}
	.search {display:block;	margin:auto; margin-bottom:10px;}	
	.add {display:inline-block;	margin-bottom:20px;}
</style>
<script>
	function cancel() {
		document.getElementById("id").removeAttribute("required");
		document.getElementById("name").removeAttribute("required");
		document.getElementById("price").removeAttribute("required");
		document.getElementById("stock").removeAttribute("required");
	}
</script>
</head>
<body>
	<h1>
		<spring:message code="application.name" />
	</h1>
	
	<spring:url var="processUrl" value="/item/process"/>
	
<c:choose>
	<c:when test="${pageContext.request.queryString eq 'button=search'}">
	<form:form modelAttribute="item" action="${processUrl}" method="post">
	<div class="container">
		<table width=200 class="search">
			<tr>
				<th class="header" colspan=2>Search Item by ID</th>
			</tr>
			<tr><td class="spacer"> </td></tr>
			<tr>
				<td><form:label path="stockID"></form:label>&nbsp;<spring:message code="item.id" /></td>
				<td><form:input path="stockID" required="true" id="id"/></td>
			</tr>
			<tr>
				<td colspan=2 class="errors"> &nbsp;
					<c:if test="${not empty errorID}">
						<c:out value="${errorID}"/>
					</c:if>
				</td>
			</tr>
		</table>
			<input type="submit" value=" Search ID " name="findID" class="search"/>
		<br/>
	</div>
	</form:form>
		<br/>
	<form:form modelAttribute="item" action="${processUrl}" method="post">
	<div class="container">
		<table width=225 class="search">
			<tr>
				<th  class="header" colspan=2>Search Item by Name</th>
			</tr>
			<tr><td class="spacer"> </td></tr>
			<tr>
				<td><form:label path="itemName"></form:label><spring:message code="item.name" /></td>
				<td><form:input path="itemName" required="true" id="name"/></td>
			</tr>
			<tr>
				<td colspan=2 class="errors"> &nbsp;
					<c:if test="${not empty errorName}">
						<c:out value="${errorName}"/>
					</c:if>
				</td>
			</tr>
		</table>
			<input type="submit" value=" Search Name " name="findName" class="search"/>
			<br/>
	</div>
	</form:form>
	<form:form modelAttribute="item" action="${processUrl}" method="post">
	<div>
		<br/>
		<input type="submit" value=" Cancel " name="list" onclick="cancel();" class="search"/>
	</div>
	</form:form>
	</c:when>
		
	<c:otherwise>
	<form:form modelAttribute="item" action="${processUrl}" method="post">
	<div class="container">
		<table width="300">
		<tr>
			<th colspan=2><spring:message code="item.details" /></th>
		</tr>
		<tr><td class="spacer"> </td></tr>
		<tr>
			<td><form:label path="stockID"></form:label><spring:message code="item.id" /></td>
			<td><form:input path="stockID" required="true" id="id"/></td>
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
			<td><form:input path="unitPrice" required="true" id="price"/></td>
		</tr><tr>
			<td colspan=2><form:errors path="unitPrice" class="errors"/></td>
		</tr>
		<tr>
			<td><form:label path="onStock"></form:label><spring:message code="item.stock" /></td>
			<td><form:input path="onStock" required="true" id="stock"/></td>
		</tr><tr>
			<td colspan=2><form:errors path="onStock" class="errors"/></td>
		</tr>
		</table>
		<br/>
		<input type="submit" value=" Add Item " name="save" class="add"/>			
		<input type="submit" value=" Cancel " name="list" onclick="cancel();" class="add"/>
	</div>
	</form:form>	
	</c:otherwise>
	</c:choose>	
</body>
</html>