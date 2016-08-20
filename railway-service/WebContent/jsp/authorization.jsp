<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.0"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt">
	<fmt:setBundle basename="property.messages" />
	<jsp:directive.page contentType="text/html; charset=UTF-8" />

	<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/style.css" />

</head>
<body>
	<header>
		<jsp:directive.include file="/jspf/header.jspf" />
		<jsp:directive.include file="/jspf/menu.jspf" />
	</header>
	<div class="span">
		<jsp:directive.include file="/jspf/left.jspf" />
		<div class="middle">
			<c:if test="${errorMessage != null }">
				<h1>${errorMessage}</h1>
			</c:if>
			<form method="POST" action="Controller"
				accept-charset="UTF-8" name="form1">
				<table class="loginForm">


					<input type="hidden" name="command" value="login" />
					<br />
					<input type="hidden" name="typeMes" value="password" />

					<caption>
						<fmt:message key="title.login.form"/>
					</caption>
					<tr>
						<td class="loginForm1"><fmt:message key="login">
							</fmt:message></td>
						<td class="loginForm2"><input type="text" name="login" /></td>
					</tr>
					<tr>
						<td class="loginForm1"><fmt:message key="password">
							</fmt:message></td>
						<td class="loginForm2"><input type="password" name="password" />
						</td>
					</tr>
					<tr>
						<td colspan="2" align="center">
						<c:set var="submit"> <fmt:message key="submit" /> </c:set> 
						<input type="button" name="SUBMIT" value="${submit}"
							onclick="document.form1.command.value = 'login'; document.form1.submit();" /></td>

					</tr>
					<tr>
						<td align="left">
						<c:set var="registration"> <fmt:message key="registration" /> </c:set> 
						<input type="button" style="width: 100px; height: 20px; font-size: 10px; border: none; 
						background: transparent" name="SUBMIT" value="${registration}"
							onclick="document.form1.command.value = 'registration'; document.form1.submit();" />
						</td>
						<td align="right"><c:set var="recover">
								<fmt:message key="recover.password" />
							</c:set> <input type="button"
							style="width: 100px; height: 20px; font-size: 10px; border: none; background: transparent"
							name="SUBMIT" value="${recover}"
							onclick="document.form1.command.value = 'message'; document.form1.submit();" /></td>
					</tr>

				</table>
			</form>
		</div>
		<jsp:directive.include file="/jspf/right.jspf" />
	</div>
	<div style="clear: both"></div>
	<div>
		<jsp:directive.include file="/jspf/footer.jspf" />
	</div>
	
</body>
	</html>
</jsp:root>