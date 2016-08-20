<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
	xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.0"
	xmlns:fmt="http://java.sun.com/jsp/jstl/fmt">
	<fmt:setBundle basename="property.messages" />
	<jsp:directive.page contentType="text/html; charset=UTF-8" />
	<html>
<head>
<title>AdminPage</title>
<link rel="stylesheet" type="text/css" href="css/style.css" />

</head>
<body>
	<header>
		<a name= "up"/>
		<jsp:directive.include file="/jspf/adminHeader.jspf" />
	</header>

	<div class="line">
		<hr color="grey" width="900px" size="5px" align = "left" />
	</div>

	<div class="span">

		<div id="leftside1" class="span">
		<a href = "#up">
			<img alt="UP" src="./images/up-1.png"/>
		</a>
		</div>

	<div class="adminMiddle">
			<c:if test="${errorMessage != null}">
				<h1>${errorMessage}</h1>
			</c:if>

			<c:if test="${message != null}">
			<h1>${message}</h1>
			</c:if>

			<c:set var="submit">
				<fmt:message key="send" />
			</c:set>
			<c:set var="type">
				<fmt:message key="type" />
			</c:set>
			<c:set var="message">
				<fmt:message key="message" />
			</c:set>
			<c:set var="password">
				<fmt:message key="password" />
			</c:set>

			<ol type="I">
				<li class="mainMenu"><a href="#password"><fmt:message
							key="password" /></a></li>
				<ol type="1">
					<li class="submenu"><a href="#password.change"><fmt:message
								key="password.change" /></a></li>
				</ol>
				<li class="mainMenu"><a href="#user"><fmt:message
							key="user" /></a></li>
				<ol type="1">
					<li class="submenu"><a href="#user.login"><fmt:message
								key="select.all" /></a></li>
					<li class="submenu"><a href="#user.status"><fmt:message
								key="change.status" /></a></li>
				</ol>

				<li class="mainMenu"><a href="#message"><fmt:message
							key="message" /></a></li>
				<ol type="1">

					<li class="submenu"><a href="#message.select.all"><fmt:message
								key="select.all" /></a></li>
				</ol>
			</ol>


			<div class="line">
				<hr color="grey" width="900px" size="5px" align = "left"/>
			</div>


			<ol type="I">
				<li class="mainMenu">
					<a name="password"><fmt:message key="password" /></a>
				</li>
				<ol type="1">
					<li class="submenu"><a name="password.change"><fmt:message
								key="password.change" /></a>
						<form method="POST" action="Controller"
							accept-charset="UTF-8">
							<input type="hidden" name="command" value="admin" /> <input
								type="hidden" name="admin" value="password" />
							<table>
								<tr>
									<td class="adminForm1"><fmt:message key="enter.user.login" /></td>
									<td><input type="text" name="login" required="login" /></td>
								</tr>
								<tr>
									<td class="adminForm1"><fmt:message
											key="enter.new.password" /></td>
									<td><input type="text" name="password" /></td>

								</tr>
								<tr>
									<td colspan="2"><input type="submit" name="submit"
										value="${submit}" /></td>
								</tr>
							</table>
						</form></li>
				</ol>


				<li class="mainMenu">
                    <a name="user"><fmt:message key="user" /></a>
                </li>
				<ol type="1">
					<li class="submenu"><a name="user.login"><fmt:message
								key="select.all" /></a>
						<form method="POST" action="Controller"
							accept-charset="UTF-8">
							<input type="hidden" name="command" value="admin" /> <input
								type="hidden" name="admin" value="user" /> <input
								type="hidden" name="type" value="select" />
							<table>

								<tr>
									<td colspan="2">
                                        <input type="submit" name="submit"
										value="${submit}" /></td>
								</tr>
							</table>
						</form></li>


					<li class="submenu"><a name="user.status"><fmt:message
								key="change.status" /></a>
						<form method="POST" action="Controller"
							accept-charset="UTF-8">
							<input type="hidden" name="command" value="admin" /> <input
								type="hidden" name="admin" value="user" /> <input
								type="hidden" name="type" value="change" />
							<table>
								<tr>
									<td class="adminForm1"><fmt:message key="enter.login" /></td>
									<td><input type="text" name="login" required="login" /></td>
								</tr>
								<tr>
									<td class="adminForm1"><fmt:message
											key="enter.access.level" /></td>
									<td><input type="text" name="level" /></td>
								</tr>
								<tr>
									<td colspan="2"><input type="submit" name="submit"
										value="${submit}" /></td>
								</tr>
							</table>
						</form></li>
				</ol>
				<li class="mainMenu"><a name="message"><fmt:message key="message" /></a></li>
				<ol type="1">
					<li class="submenu"><a name="message.select.all"><fmt:message key="select.all" /></a>
						<form method="POST" action="Controller" accept-charset="UTF-8">
							<input type="hidden" name="command" value="admin" />
							<input type="hidden" name="admin" value="message" />
							<table>
								<tr>
									<td colspan="2"><input type="submit" name="submit"
										value="${submit}" /></td>
								</tr>
							</table>
						</form></li>

				</ol>
			</ol>
		</div>

	</div>
	<div style="clear: both;"></div>

</body>
	</html>
</jsp:root>