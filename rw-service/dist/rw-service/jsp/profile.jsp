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
			<div class="span1">
				<ul>
					<li class="adminMenu">
						<form method="POST" action="Controller">
							<input type="hidden" name="command" value="page" /> 
							<input type="hidden" name="pageName" value="HOME_PAGE" /> 
							<c:set var="returnVar">
								<fmt:message key="to.home.page" />
							</c:set>
							<input type="submit" name="SUBMIT" value="${returnVar}" />
						</form>
					</li>
					<li class="adminMenu">
					<form method="POST" action="Controller">
							<input type="hidden" name="command" value="page" /> 
							<input type="hidden" name="pageName" value="ACCOUNT_PAGE" /> 
							<c:set var="returnVar1">
								<fmt:message key="add.bill.to.account" />
							</c:set>
							<input type="submit" name="SUBMIT" value="${returnVar1}" />
						</form>
						</li>
					<li class="adminMenu"><span class="language">
							<form method="POST" action="Controller" accept-charset="UTF-8">
								<input type="hidden" name="command" value="language" /> <input
									type="hidden" name="language" value="EN" /> <input
									type="hidden" name="pageName"
									value="${pageContext.request.requestURI}" />
								<button type="submit" name="Submit">
									<img alt="English" src="images/en.gif" />
								</button>
							</form>
					</span></li>
					<li class="adminMenu"><span class="language">
							<form method="POST" action="Controller" accept-charset="UTF-8">
								<input type="hidden" name="command" value="language" /> <input
									type="hidden" name="language" value="RU" /> <input
									type="hidden" name="pageName"
									value="${pageContext.request.requestURI}" />
								<button type="submit" name="Submit">
									<img alt="Russish" src="images/ru.gif" />
								</button>
							</form>
					</span></li>
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

			<div class="select">
				<form method="POST" action="Controller"
					accept-charset="UTF-8">

					<table>
						<tr>
							<h1>
								<strong><fmt:message key="bought.tickets" /></strong>
							</h1>
						</tr>

						<tr>
							<fmt:message key="watch.tickets" />:
						</tr>
						<tr>
							<td><input type="hidden" name="select" value="startTime" />
								<input type="date" name="timeDep" value="dateBuy" length="20" />
							</td>
							
							<td><input type="hidden" name="select" value="endTime" /> <input
								type="date" name="timeDep" value="dateBuy" length="20" /></td>
						</tr>
						<tr>
							<td colspan="2"><input type="submit"
								value=<fmt:message key="search" /> /> <br /></td>
						</tr>


					</table>

					<c:if test="${not empty dateBuy}">
						<table border="1" style="width: 550px">

							<th><fmt:message key="number.ticket" /></th>
							<th><fmt:message key="number.train" /></th>
							<th><fmt:message key="number.wagon" /></th>
							<th><fmt:message key="number.place" /></th>
							<th><fmt:message key="price" /></th>

							<c:forEach items="${ticketList}" var="ticket">

								<tr>
									<td><c:out value="${ticket.ticketNumber}" /></td>
									<td><c:out value="${ticket.trainNumber}" /></td>
									<td><c:out value="${ticket.wagonNumber}" /></td>
									<td><c:out value="${ticket.placeNumber}" /></td>
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
