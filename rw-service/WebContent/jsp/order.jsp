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

			<div class="select">
				<form method="POST" action="Controller" accept-charset="UTF-8">

					<table>
						<tr>
							<h1>
								<strong><fmt:message key="order" /></strong>
							</h1>
						</tr>
						<tr>
							<td><fmt:message key="station.departure" />:</td>
							<td><input type="hidden" name="select" value="stationDep" />
								<c:set var="man">
									<fmt:message key="search.station" />
								</c:set> <input type="text" name="stationDep" value="" length="20"
								placeholder="${man}" /></td>

							<td><fmt:message key="station.arrival" />:</td>
							<td><input type="hidden" name="select" value="stationArr" />
								<c:set var="man">
									<fmt:message key="search.station" />
								</c:set> <input type="text" name="stationArr" value="" length="20"
								placeholder="${man}" /></td>
						</tr>
						<tr>
							<td><fmt:message key="departure.date" />:</td>
							<td><input type="hidden" name="select" value="timeDep" /> <input
								type="date" name="timeDep" value="" length="20" />
						</tr>
						<tr>
						</tr>
						<tr>
							<td colspan="2"><input type="hidden" name="command"
								value="find_order" /> <input type="submit"
								value=<fmt:message key="next" /> /> <br /></td>
						</tr>
					</table>
				</form>
			</div>

			<c:if
				test="${not empty stationDep and not empty stationArr and not empty timeDep}">
				<table border="1" style="width: 200px">
					<tr>
						<td><fmt:message key="station.departure" /></td>
						<td><c:out value="${stationDep}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="station.arrival" /></td>
						<td><c:out value="${stationArr}" /></td>
					</tr>
					<tr>
						<td><fmt:message key="departure.date" /></td>
						<td><c:out value="${timeDep}" /></td>
					</tr>
				</table>
			</c:if>
			<br />
			<c:if test="${not empty trainList}">
				<table border="1" style="width: 550px">

					<th><fmt:message key="number.train" /></th>
					<th><fmt:message key="departure.date" /></th>
					<th><fmt:message key="arrival.date" /></th>
					<th><fmt:message key="social.price" /></th>

					<c:forEach items="${trainList}" var="train">
						<tr>
							<td><c:out value="${train.trainName}" /></td>
							<td><c:out value="${train.timeDeparture}" /></td>
							<td><c:out value="${train.timeArrival}" /></td>
							<td><c:out value="${train.timeTrevel}" /></td>
							<td><c:out
									value="${train.sizePlaceGeneral} / ${train.pricePlaceGeneral}" /></td>
							<td><c:out
									value="${train.sizePlacePlac} / ${train.pricePlacePlac}" /></td>
							<td><c:out
									value="${train.sizePlaceCupe} / ${train.pricePlaceCupe}" /></td>
							<td><c:out
									value="${train.sizePlaceSv} / ${train.pricePlaceSv}" /></td>
							<c:if test="${accessLevel == 1}">
							<td>
									<form method="POST" action="Controller" accept-charset="UTF-8">
										<input type="hidden" name="command" value="select_ticket_set" />
										<input type="hidden" name="select_train_id"
											value="${train.trainId}" /> <input type="submit"
											value=<fmt:message key="buy" /> />
									</form>
									</td>
								</c:if>
						</tr>
					</c:forEach>

				</table>
			</c:if>
		</div>

</div>


		<div style="clear: both"></div>

		<div>
			<jsp:directive.include file="/jspf/footer.jspf" />
		</div>
</body>
</html>