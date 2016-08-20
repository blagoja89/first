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
							<input type="hidden" name="command" value="page" /> <input
								type="hidden" name="pageName" value="PROFILE_PAGE_PATH" />
							<c:set var="returnVar">
								<fmt:message key="to.profile.page" />
							</c:set>
							<input type="submit" name="SUBMIT" value="${returnVar}" />
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

			<c:if test="${message != null }">
				<h1>${message}</h1>
			</c:if>
			<c:if test="${errorMessage != null }">
				<h1>${errorMessage}</h1>
			</c:if>
			<div class="select">
				<form method="POST" action="Controller" accept-charset="UTF-8">

					<form method="POST" action="Controller" accept-charset="UTF-8">
						<table>
							<tr>
								<h1>
									<strong><fmt:message key="account" /></strong>
								</h1>
							</tr>
							<tr>
							</tr>

							<tr>
								<fmt:message key="your.checking.account" />
								:
							</tr>
							<tr>
								<td>${user.account.bill}</td>
							<tr>
								<td><fmt:message key="add.bill.to.account" /></td>
							</tr>
							<td><input type="number" name="userBill" value="" /></td>
							</tr>
							<tr>
								<input type="hidden" name="command" value="add_to_bill" />
								<input type="submit" value=<fmt:message key="add" /> />
							</tr>


						</table>
					</form>


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
