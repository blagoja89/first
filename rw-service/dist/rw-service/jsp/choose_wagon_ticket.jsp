<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<%@ page import="java.util.*"%>
<!DOCTYPE html PUBLIC "-//W3C//DTD HTML 4.01 Transitional//EN" "http://www.w3.org/TR/html4/loose.dtd">
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
			<c:if
				test="${not empty selectedTrain}">
				<table border="1" style="width: 200px">
					<tr>
						<td><fmt:message key="train.name" /></td>
						<td><c:out value="${selectedTrain.trainName}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="station.departure" /></td>
						<td><c:out value="${selectedTrain.stationDeparture}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="station.arrival" /></td>
						<td><c:out value="${selectedTrain.stationArrival}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="departure.date" /></td>
						<td><c:out value="${selectedTrain.timeDeparture}" /></td>
						<td><fmt:message key="arrival.date" /></td>
						<td><c:out value="${selectedTrain.timeArrival}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="time.trevel" /></td>
						<td><c:out value="${selectedTrain.timeTrevel}" /></td>
					</tr>
				</table>
			</c:if>
			<br />
			<table border="1" style="width: 550px">

				<th><fmt:message key="wagon.number" /></th>
				<th><fmt:message key="wagon.type" /></th>
				<th><fmt:message key="wagon.size.place" /></th>

				<form method="POST" action="Controller" accept-charset="UTF-8">
					<c:forEach items="${selectedTrain.wagonWrappers}" var="ww">
						<tr>
							<td><c:out value="${ww.wagon.number}" /></td>
							<td><c:out value="${ww.wagon.type}" /></td>
							<td><c:out value="${ww.price}" /></td>							
							<td><select name="placeItem" onchange="return setValue();">
									<option>
										<fmt:message key="wagon.number" />
									</option>
									<c:forEach items="${ww.freePlace}" var="place">
										<option value="${place}">
											<c:out value="${place}" />
										</option>
									</c:forEach>
							</select></td>
							<td><c:if test="${true}">
									<input type="hidden" name="command" value="buy_ticket" />
									<input type="hidden" name="select_wagon" value="${ww.wagon.id}" />

									<input type="submit" value=<fmt:message key="buy" /> />
								</c:if></td>
						</tr>
					</c:forEach>
				</form>

			</table>

		</div>

	</div>

		<div style="clear: both"></div>

		<div>
			<jsp:directive.include file="/jspf/footer.jspf" />
		</div>
</body>
</html>