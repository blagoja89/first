<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="property.messages" />
<fmt:setLocale value="${language}" scope="session" />
<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=UTF-8">
<link rel="stylesheet" type="text/css" href="css/style.css" />
<title>Page with info of users</title>
</head>
<body>

	<header> <a name="up" /> <%@ include
		file="/jspf/adminHeader.jspf"%> 
	</header>

	<div class="line">

		<hr color="grey" width="900px" size="5px" align = "left"/>
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
					<td><fmt:message key="login" /></td>
					<td><fmt:message key="password" /></td>
					<td><fmt:message key="firstname" /></td>
					<td><fmt:message key="lastname" /></td>
					<td><fmt:message key="email" /></td>
					<td><fmt:message key="deleted" /></td>
					<td><fmt:message key="role" /></td>
					<td><fmt:message key="placeOfBirth" /></td>
					<td><fmt:message key="city" /></td>
					<td><fmt:message key="address" /></td>
					<td><fmt:message key="homePhone" /></td>
					<td><fmt:message key="mobilePhone" /></td>
					<td><fmt:message key="job" /></td>
					<td><fmt:message key="position" /></td>
					<td><fmt:message key="income" /></td>
					<td><fmt:message key="reservist" /></td>
					<td><fmt:message key="pensioner" /></td>
					<td><fmt:message key="familyStatus" /></td>

					<td><fmt:message key="nationality" /></td>
					<td><fmt:message key="disability" /></td>
					<td><fmt:message key="series" /></td>
					<td><fmt:message key="no" /></td>
					<td><fmt:message key="placeOfIssue" /></td>
					<td><fmt:message key="dateOfIssue" /></td>
					<td><fmt:message key="identificationno" /></td>
					<td><fmt:message key="birthday" /></td>

				</tr>
				<c:forEach items="${userList}" var="client">

					<tr>
						<td>"${client.id}"</td>
						<td>"${client.login}"</td>
						<td>"${client.password}"</td>
						<td>"${client.firstName}"</td>
						<td>"${client.lastName}"</td>
						<td>"${client.email}"</td>
						<td>"${client.deleted}"</td>
						<td>"${client.role}"</td>
						<td>"${client.userInf.clientAdditionalInformation.placeOfBirth}"</td>
						<td>"${client.userInf.clientAdditionalInformation.city}"</td>
						<td>"${client.userInf.clientAdditionalInformation.address}"</td>
						<td>"${client.userInf.clientAdditionalInformation.homePhone}"</td>
						<td>"${client.userInf.clientAdditionalInformation.mobilePhone}"</td>
						<td>"${client.userInf.clientAdditionalInformation.job}"</td>
						<td>"${client.userInf.clientAdditionalInformation.position}"</td>
						<td>"${client.userInf.clientAdditionalInformation.income}"</td>
						<td>"${client.userInf.clientAdditionalInformation.reservist}"</td>
						<td>"${client.userInf.clientAdditionalInformation.pensioner}"</td
						<td>"${client.userInf.clientAdditionalInformation.familyStatus}"</td>

						<td>"${client.userInf.clientAdditionalInformation.nationality}"</td>
						<td>"${client.userInf.clientAdditionalInformation.disability}"</td>
						<td>"${client.userInf.passportData.series}"</td>
						<td>"${client.userInf.passportData.no}"</td>
						<td>"${client.userInf.passportData.placeOfIssue}"</td>
						<td>"${client.userInf.passportData.dateOfIssue}"</td>
						<td>"${client.userInf.passportData.identificationNo}"</td>
						<td>"${client.userInf.birthday}"</td>

					</tr>
				</c:forEach>

			</table>
		</div>



	</div>
	<div style="clear: both"></div>

	<div></div>

</body>
</html>
