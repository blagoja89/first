<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.0"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt">
    <fmt:setBundle basename="property.messages"/>
    <jsp:directive.page contentType="text/html; charset=UTF-8"/>
    <html>
    <head>
        <link rel="stylesheet" type="text/css" href="/css/style.css"/>

    </head>
    <body>
    <header>
        <jsp:directive.include file="/jspf/header.jspf"/>
        <jsp:directive.include file="/jspf/menu.jspf"/>
    </header>

    <div class="span">
        <jsp:directive.include file="/jspf/left.jspf"/>
        <div class="middle">
            <h2>
                <fmt:message key="about.us"/>
            </h2>

            <p>Приветствуем Вас на нашем сайте!</p>

            <p><strong> ОAO  «Минский моторный завод»  </strong></p>

            <p>     Открытое акционерное общество «Управляющая компания холдинга «МИНСКИЙ МОТОРНЫЙ ЗАВОД»
                расположен в юго-восточной части г. Минска. Расстояние от центра города составляет около 3,5 км.
                С сетью железных дорог завод связан подъездным путем протяженностью около 2 км., связанным со
                станцией Степянка магистрали Москва-Брест. Промышленная площадка ОАО «УКХ  «ММЗ» размещена на
                площади 32,13 гектара, производственные площади под основное производство составляют 8 гектаров.
                Имеющиеся производственные площади обеспечены инженерными коммуникациями.</p>

            <p><strong>
                ОАО «УКХ «ММЗ» серийно выпускает много целевые дизельные двигатели внутреннего сгорания
                размерностью 110х125 мм и 110х140 мм в мощностном диапазоне:  </strong></p>
            <ul>
                <li>3-х цилидровые дизельные двигатели     26 кВт (35 л.с.);</li>
                <li>4-х цилиндровые дизельные двигатели от 44,2 кВт (60 л.с.) до 114,0 кВт (155 л.с.);</li>
                <li>6-ти цилиндровые дизельные двигатели от 95,7 кВт (130 л.с.) до 184,0 кВт (250 л.с.);</li>
                <li>8-ми цилиндровые дизельные двигатели от 312,5 до 331,2 кВт (425-450 л.с.).</li>
            </ul>

            <h4>    Минский моторный завод производит свыше 250 модификаций двигателей и около 1000 исполнений.
                Это позволяет в кратчайшие сроки адаптировать комплектацию двигателя под конкретного потребителя.</h4>

            <p>С 1998 г. ОАО «ММЗ» начал производство двигателей Евро-1.</p>

            <p>С 2003 г. ОАО «ММЗ» начал производство двигателей Евро-2.</p>

            <p>С 2008 г. ОАО «ММЗ» начал производство 8 цилиндровых двигателей.</p>

            <h1>Напишите нам письмо!</h1>

            <form method="POST" action="Controller" accept-charset="UTF-8">
                <input type="hidden" name="command" value="message"/>
                <input type="hidden" name="typeMes" value="message"/>
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
                        <td><textarea name="message" value="" cols="45" rows="10"><fmt:message
                                key="message"/></textarea></td>
                    </tr>
                    <tr>
                        <td></td>
                        <td>
                            <c:set var="submit"> <fmt:message key="send"/>
                            </c:set> <input type="submit" name="SUBMIT" value="${submit}"/></td>
                    </tr>
                    </tbody>
                </table>
            </form>
        </div>
        <jsp:directive.include file="/jspf/right.jspf"/>
    </div>
    <div style="clear: both"></div>
    <div>
        <jsp:directive.include file="/jspf/footer.jspf"/>
    </div>
    </body>
    </html>
</jsp:root>