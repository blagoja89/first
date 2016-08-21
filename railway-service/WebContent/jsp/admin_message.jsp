<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="property.messages" />
<fmt:setLocale value="${language}" scope="session" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<title>Page with info about messages</title>
</head>
<body>


	<header>
		<a name="up" />
		<%@ include file="/jspf/adminHeader.jspf"%>
	</header>

	<div class="line">

		<hr color="grey" width="900px" size="5px" align="left" />
	</div>

	<div class="span">

		<div id="leftside1" class="span">
			<a href="#up"> <img alt="UP" src="./images/up-1.png" />
			</a>
		</div>

		<div class="adminMiddle">


			<div align="center">
				<h1>
					<fmt:message key="results" />
				</h1>
				<c:if test="${not empty message}">
					<h1>${message}</h1>
				</c:if>
				<c:if test="${not empty errorMessage}">
					<h1>${errorMessage}</h1>
				</c:if>

			</div>

			<table class="results" border="1" align="center">

				<tr>
					<td><fmt:message key="id" /></td>
					<td><fmt:message key="name" /></td>
					<td><fmt:message key="mail" /></td>
					<td><fmt:message key="message" /></td>
					<td><fmt:message key="type" /></td>

				</tr>
				<c:forEach items="${messages}" var="message">

					<tr>
						<td>"${message.id}"</td>
						<td>"${message.name}"</td>
						<td>"${message.mail}"</td>
						<td>"${message.message}"</td>
						<td>"${message.type}"</td>
					</tr>
				</c:forEach>

			</table>



	</div>
	<div style="clear: both"></div>

	<div></div>

</body>
</html>
