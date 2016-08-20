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
	<header>
		<jsp:directive.include file="/jspf/header.jspf" />
		<jsp:directive.include file="/jspf/menu.jspf" />
	</header>

	<div class="span">
		<jsp:directive.include file="/jspf/left.jspf" />
		<div class ="middle">
			<h2>
				<fmt:message key="about.us" />
			</h2>
			<p>
				Приветствуем Вас в онлайн-кассе жд билетов <a
					href="jsp/home.jsp">Railway-service</a>
			</p>
			<p><strong> Железнодорожные билеты. Заказ онлайн </strong></p>
			<ul>
			<li>
				Мы предлагаем совершить покупку билета с помощью интернета 
				в любом удобном для вас месте: из дома, офиса, при помощи 
				компьютера, нетбука или смартфона.
			</li>
			<li>Размер нашего сервисного сбора для заказа ж/д билетов 
				один из самых низких на рынке.</li>
			<li>
				У нас вы можете уточнить актуальное расписание поездов 
				дальнего следования.
			</li>
			<li>Вы можете заказать подбор наиболее удобного маршрута 
				для командировки или отдыха, а также подбор рейса в тех 
				случаях, если вы самостоятельно затрудняетесь с 
				определением точек следования железнодорожного состава.</li>
				</ul>
			
			<h4>Реквизиты ООО «Railway-service»:</h4>
			<p>УНП 282733248</p>
			<p>Адрес:</p>
			<p>р.с. №</p>
			<p>Директор</p>
			<p>Свидетельство о гос. регистрации номер</p>
			<h1>Ответим на Ваши вопросы</h1>
			<form method="POST" action="Controller" accept-charset="UTF-8">
				<input type = "hidden" name = "command" value = "message"/>
				<input type = "hidden" name = "typeMes" value = "message"/>
				<table>
					<tbody>
						<tr>
							<td><fmt:message key="username"></fmt:message></td>
							<td><input type="text" maxlength="20" name="name" value=""/></td>
						</tr>
						<tr>
							<td><fmt:message key="email"></fmt:message></td>
							<td><input type="text" maxlength="100" name="mail" value=""/></td>
						</tr>
						<tr>
							<td><fmt:message key="message"></fmt:message></td>
							<td><textarea name="message" value="" cols="45" rows="10"><fmt:message key = "message"/></textarea></td>
						</tr>
						<tr>
							<td></td>
							<td>
							<c:set var="submit"> <fmt:message key="send" />
							</c:set> <input type="submit" name="SUBMIT" value="${submit}" /></td>
						</tr>
					</tbody>
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