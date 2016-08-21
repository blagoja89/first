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
								type="hidden" name="pageName" value="HOME_PAGE" />
							<c:set var="returnVar">
								<fmt:message key="to.home.page" />
							</c:set>
							<input type="submit" name="SUBMIT" value="${returnVar}" />
						</form>
					</li>
					<li class="adminMenu">
						<form method="POST" action="Controller">
							<input type="hidden" name="command" value="page" /> <input
								type="hidden" name="pageName" value="PROFILE_PAGE_PATH" />
							<c:set var="returnVar1">
								<fmt:message key="to.profile.page" />
							</c:set>
							<input type="submit" name="SUBMIT" value="${returnVar1}" />
						</form>
					</li>
				</ul>
			</div>
		</div>
		<div style="clear: both"></div>

	</header>

	<hr color="grey" width="900px" size="5px" align="left" />

	<div class="span">

		<jsp:directive.include file="/jspf/left.jspf" />

		<div class="middle">
			<c:if test="${message != null}">
				<h1>${message}</h1>
			</c:if>
			<c:if test="${errorMessage != null}">
				<h1>${errorMessage}</h1>
			</c:if>


			<form name="loginForm" method="POST" action="Controller"
				accept-charset="UTF-8">
				<input type="hidden" name="command" value="change_profile" /> <br />
				<table class="loginForm">

					<tr>
						<td class="loginForm1"><fmt:message key="password">
							</fmt:message></td>
						<td class="loginForm2"><input type="password" name="password" /></td>
					</tr>
					<tr>
						<td class="loginForm1"><fmt:message key="email">
							</fmt:message></td>
						<td class="loginForm2"><input type="text" name="email"
							value="${mail}" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><c:set var="submit">
								<fmt:message key="submit" />
							</c:set> <input type="submit" name="SUBMIT" value="${submit}" /></td>
					</tr>
				</table>
			</form>
		</div>
	</div>
	<div style="clear: both;"></div>
	<div>
		<jsp:directive.include file="/jspf/footer.jspf" />
	</div>
</body>
</html>
