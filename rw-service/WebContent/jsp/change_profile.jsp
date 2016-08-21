<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.0"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt">
	<fmt:setBundle basename="property.messages" />
	<jsp:directive.page contentType="text/html; charset=UTF-8" />
	<html>
<head>
<link rel="stylesheet" type="text/css" href="/css/style.css" />

</head>
<body>
	<header class="header">
		<div class="row">
			<div class="span1">
				<ul>
					<li class="adminMenu">
						<form method="POST" action="Controller">
							<input type="hidden" name="command" value="page" /> <input
								type="hidden" name="pageName" value="HOMEPAGE_PATH" />
							<c:set var="returnVar">
								<fmt:message key="TO_HOME_PAGE" />
							</c:set>
							<input type="submit" name="SUBMIT" value="${returnVar}" />
						</form>
					</li>
					<li class="adminMenu">
						<form method="POST" action="Controller">
							<input type="hidden" name="command" value="page" /> <input
								type="hidden" name="pageName" value="PROFILE_PAGE_PATH" />
							<c:set var="returnVar1">
								<fmt:message key="TO_PROFILE_PAGE" />
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

	<div class="span">
		
			<jsp:directive.include file="/jspf/left.jspf" />
		
		<div class = "middle">

			<form name="loginForm" method="POST"
				action="Controller" accept-charset="UTF-8">
				<input type="hidden" name="command" value="changeProfile" /> <br />
				<table class="loginForm">

					<tr>
						<td class="loginForm1"><fmt:message key="PASSWORD">
							</fmt:message></td>
						<td class="loginForm2"><input type="text" name="password" /></td>
					</tr>
					<tr>
						<td class="loginForm1"><fmt:message key="EMAIL">
							</fmt:message></td>
						<td class="loginForm2"><input type="text" name="email"
							value="${mail}" /></td>
					</tr>
					<tr>
						<td colspan="2"><input type="hidden"
							name="Login" value="${login}" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center"><c:set var="submit">
								<fmt:message key="SUBMIT" />
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
</jsp:root>
