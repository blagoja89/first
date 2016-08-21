<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.0"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt">
	<fmt:setBundle basename="property.messages" />
	<jsp:directive.page contentType="text/html; charset=UTF-8" />
<html>
<head>
<link rel="stylesheet" type="text/css" href="../css/style.css" />
<title>Registration</title>
</head>
<body>
	
	<header>
		<jsp:directive.include file="/jspf/header.jspf" />
		<jsp:directive.include file="/jspf/menu.jspf" />
	</header>
	<div class="span">
		<jsp:directive.include file="/jspf/left.jspf" />
		<div class = "middle">
			
			<c:if test="${errorMessage != null}"> 
			<h1>${errorMessage}</h1>
			</c:if>
			
			<c:if test="${message != null}"> 
			<h1>${message}</h1>
			</c:if>
			
			
				<form name="loginForm" method="POST" action="Controller" accept-charset="UTF-8">
					<input type="hidden" name="command" value="registration" /> <br />
						<table class="loginForm">
					<tr>
						<td  colspan = "2"><fmt:message key="nota.bene">
							</fmt:message></td>
					</tr>
					<tr>
						<td class="loginForm1"><fmt:message key="login">
							</fmt:message>*</td>
						<td class="loginForm2"><input type="text" name="login" required= "login"/></td>
					</tr>
					<tr>
						<td class="loginForm1"><fmt:message key="password">
							</fmt:message>*</td>
						<td class="loginForm2"><input type="password" name="password" required = "password"/>
						</td>
					</tr>
					<tr>
						<td class="loginForm1"><fmt:message key="repassword">
							</fmt:message>*</td>
						<td class="loginForm2"><input type="password" name="repassword" required =  "repassword" />
						</td>
					</tr>
					<tr>
						<td class="loginForm1"><fmt:message key="firstname"/></td>
						<td class="loginForm2"><input type="text" name="firstname" /></td>
					</tr>
					<tr>
						<td id="loginForm1"><fmt:message key="lastname"/></td>
						<td class="loginForm2"><input type="text" name="lastname" /></td>
					</tr>
					<tr>
						<td class="loginForm1"><fmt:message key="email">
							</fmt:message></td>
						<td class="loginForm2"><input type="text" name="email" /></td>
					</tr>
					<tr>
						<td colspan="2" align="center">
							<c:set var="submit">
								<fmt:message key="submit" />
							</c:set>
							 <input type="submit" name="SUBMIT" value="${submit}" /></td>
					</tr>
				</table>
			</form>
			</div>
		<jsp:directive.include file = "/jspf/right.jspf"/>
	</div>
	<div style="clear: both;"></div>
	<div>
	<jsp:directive.include file="/jspf/footer.jspf" />
	</div>
	
</body>
</html>
</jsp:root>
			