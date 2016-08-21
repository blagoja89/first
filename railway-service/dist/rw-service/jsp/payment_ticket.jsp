<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="property.messages" />
<fmt:setLocale value="${language}" scope="session" />

<html>
<head>
<meta http-equiv="Content-Type" content="text/html; charset=ISO-8859-1">
<link rel="stylesheet" type="text/css" href="/css/style.css" />
<title>Insert title here</title>
</head>
<body>

	<div>
		<%@ include file="/jspf/header.jspf"%>
		<%@ include file="/jspf/menu.jspf"%>
	</div>

	<div class="span">

		<%@ include file="/jspf/left.jspf"%>

		<div class="middle">

			<c:if test="${message != null }">
				<h1>${message}</h1>
			</c:if>
			<c:if test="${errorMessage != null }">
				<h1>${errorMessage}</h1>
			</c:if>
			<br />
			<c:if test="${not empty selectedTrain}">
				<table border="1" style="width: 200px">
					<tr>
						<td><fmt:message key="train.name" /></td>
						<td><c:out value="${currentTicket.number}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="ticket.number" /></td>
						<td><c:out value="${currentTicket.trainName}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="station.departure" /></td>
						<td><c:out value="${currentTicket.stationDeparture}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="station.arrival" /></td>
						<td><c:out value="${currentTicket.stationArrival}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="departure.date" /></td>
						<td><c:out value="${currentTicket.timeDeparture}" /></td>
					</tr>
					<tr>
					<td><fmt:message key="arrival.date" /></td>
						<td><c:out value="${currentTicket.timeArrival}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="time.trevel" /></td>
						<td><c:out value="${currentTicket.timeTrevel}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="wagon.info" /></td>
						<td><c:out
								value="${currentTicket.wagon.number} / ${currentTicket.wagon.type}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="place" /></td>
						<td><c:out value="${currentTicket.place}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="price" /></td>
						<td><c:out value="${currentTicket.price}" /></td>
					</tr>
				</table>
				<br />
				<c:if test="${!statusTicket}">
					<form method="POST" action="Controller" accept-charset="UTF-8">
						<input type="hidden" name="command" value="payment_ticket" /> <input
							type="submit" value=<fmt:message key="buy" /> />
					</form>
				</c:if>
			</c:if>


		</div>
	</div>
	<div style="clear: both"></div>

		<div>
			<jsp:directive.include file="/jspf/footer.jspf" />
		</div>
</body>
</html>