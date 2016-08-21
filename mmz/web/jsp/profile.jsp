<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="ISO-8859-1"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt"%>
<fmt:setBundle basename="property.messages" />
<fmt:setLocale value="${language}" scope="session" />

<html>
<head>
<link rel="stylesheet" type="text/css" href="css/style.css" />

</head>
<body>

	<header class="header">
		<div class="row">
			<div id="notify" class="span1" style="width: auto">
				<ul>
					<li><%@ include file="/jspf/language.jspf"%></li>
					<li class="adminMenu">
						<form method="POST" action="Controller">
							<input type="hidden" name="command" value="page" /> <input
								type="hidden" name="pageName" value="CHANGE_PROFILE_PATH" />
							<c:set var="returnVar1">
								<fmt:message key="change.profile" />
							</c:set>
							<input type="submit" name="SUBMIT" value="${returnVar1}" />
						</form>
					</li>
					<li class="adminMenu">
						<form method="POST" action="Controller">
							<input type="hidden" name="command" value="page" /> <input
								type="hidden" name="pageName" value="ACCOUNT_PAGE" />
							<c:set var="returnVar1">
								<fmt:message key="add.bill.to.account" />
							</c:set>
							<input type="submit" name="SUBMIT" value="${returnVar1}" />
						</form>
					</li>
					<li class="adminMenu">
						<form method="POST" action="Controller">
							<input type="hidden" name="command" value="page" /> <input
								type="hidden" name="pageName" value="HOME_PAGE" />
							<c:set var="returnVar">
								<fmt:message key="to.home.page" />
							</c:set>
							<input type="submit" name="SUBMIT" value="${returnVar}" />
						</form>
					</li>
				</ul>
			</div>
		</div>
		<div style="clear: both"></div>

	</header>

	<hr color="grey" width="900px" size="5px" align="left" />

	<div>
		<jsp:directive.include file="/jspf/left.jspf" />
	</div>

	<div class="span">
		<div class="middle">
			<c:if test="${message != null}">
				<h1>${message}</h1>
			</c:if>
			<c:if test="${errorMessage != null}">
				<h1>${errorMessage}</h1>
			</c:if>


			<div class="select">

				<form name="loginForm" method="POST" action="Controller"
					accept-charset="UTF-8">
					<input type="hidden" name="command" value="find_all_tickets" /> <br />

					<c:if test="${not empty tiketsBetDate}">
						<table border="1" style="width: 550px">

							<th><fmt:message key="train.name" /></th>
							<th><fmt:message key="ticket.number" /></th>
							<th><fmt:message key="station.departure" /></th>
							<th><fmt:message key="station.arrival" /></th>
							<th><fmt:message key="departure.date" /></th>
							<th><fmt:message key="arrival.date" /></th>
							<th><fmt:message key="time.trevel" /></th>
							<th><fmt:message key="place" /></th>
							<th><fmt:message key="price" /></th>

							<c:forEach items="${tiketsBetDate}" var="ticket">

								<tr>
									<td><c:out value="${ticket.number}" /></td>
									<td><c:out value="${ticket.trainName}" /></td>
									<td><c:out value="${ticket.stationDeparture}" /></td>
									<td><c:out value="${ticket.stationArrival}" /></td>
									<td><c:out value="${ticket.timeDeparture}" /></td>
									<td><c:out value="${ticket.timeArrival}" /></td>
									<td><c:out value="${ticket.timeTrevel}" /></td>
									<td><c:out value="${ticket.place}" /></td>
									<td><c:out value="${ticket.price}" /></td>
								</tr>
							</c:forEach>
						</table>
					</c:if>

				</form>
			</div>
		</div>

	</div>
	<div style="clear: both;"></div>
	<div>
		<jsp:directive.include file="/jspf/footer.jspf" />
	</div>
</body>
</html>
