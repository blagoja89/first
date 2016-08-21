<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.0"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt">
    <jsp:directive.page contentType="text/html; charset=UTF-8"/>
    <fmt:setBundle basename="property.messages"/>
    <fmt:setBundle basename="property.messages"/>
    <fmt:setLocale value="${language}" scope="session"/>
    <html>
    <head>
        <link rel="stylesheet" type="text/css" href="/css/style.css"/>

        <title>HomePage</title>
    </head>
    <body>

    <header>
        <jsp:directive.include file="/jspf/header.jspf"/>
        <jsp:directive.include file="/jspf/menu.jspf"/>
    </header>
    <div class="span">
        <jsp:directive.include file="/jspf/left.jspf"/>

        <div class="middle">
            <c:if test="${message != null}">
                <h1>${message}</h1>
            </c:if>
            <c:if test="${errorMessage != null}">
                <h1>${errorMessage}</h1>
            </c:if>

            <h1>Добро пожаловать на сайт Минского моторного завода!</h1>


            <h2>Все выпускаемые двигатели сертифицированы на соответствие экологическим требованиям международных правил:</h2>

            <ul>
                <li>Вне дорожная техника — Stage 1, Stage 2, Stage 3A и Stage 3B.</li>
                <li>Автомобильные двигатели — Евро-1, Евро-2, Евро-3, Евро-4 и Евро-5.</li>
            </ul>


            <h2>Дизельные двигатели поставляются на 44 завода с конвейерной сборкой, расположенных в странах СНГ и дальнего зарубежья.</h2>

            <p>     Система менеджмента качества ОАО «УКХ «ММЗ» сертифицирована 10.01.2000 г.
                на соответствие требованиям международных стандартов ИСО 9001 (2000)</p>

            <h2>   Руководство: </h2>

            <p> Генеральный директор ОАО „УКХ „ММЗ”:	Емельянович Игорь Вячеславович</p>
            <p> И. о. коммерческий директор — 1-й зам. ген. директора: 	Четырбок Иван Александрович</p>
            <p> Технический директор — руковод. НТЦ: Анушкевич Игорь Константинович</p>


        </div>
        <jsp:directive.include file="/jspf/right.jspf"/>
    </div>
    <div style="clear: both;"></div>
    <div>
        <jsp:directive.include file="/jspf/footer.jspf"/>
    </div>
    </body>
    </html>
</jsp:root>