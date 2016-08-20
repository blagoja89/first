<%@ page language="java" contentType="text/html; charset=UTF-8"
         pageEncoding="ISO-8859-1" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@taglib uri="http://java.sun.com/jsp/jstl/fmt" prefix="fmt" %>
<%@ page session="true" %>
<fmt:setBundle basename="property.messages"/>
<fmt:setLocale value="${language}" scope="session"/>

<html>
<head>
    <link rel="stylesheet" type="text/css" href="css/style.css"/>

</head>
<body>
<header class="header">
    <div class="row">
        <div id="notify" class="span1" style="width: auto">
            <ul>
                <li>
                    <%@ include file="/jspf/language.jspf" %>
                </li>
                <li class="adminMenu">
                    <form method="POST" action="Controller">
                        <input type="hidden" name="command" value="page"/> <input
                            type="hidden" name="pageName" value="HOME_PAGE"/>
                        <c:set var="returnVar">
                            <fmt:message key="to.home.page"/>
                        </c:set>
                        <input type="submit" name="SUBMIT" value="${returnVar}"/>
                    </form>
                </li>
                <li class="adminMenu">
                    <form method="POST" action="Controller">
                        <input type="hidden" name="command" value="page"/> <input
                            type="hidden" name="pageName" value="PROFILE_PAGE_PATH"/>
                        <c:set var="returnVar1">
                            <fmt:message key="to.profile.page"/>
                        </c:set>
                        <input type="submit" name="SUBMIT" value="${returnVar1}"/>
                    </form>
                </li>
            </ul>
        </div>
    </div>
    <div style="clear: both"></div>

</header>

<hr color="grey" width="900px" size="5px" align="left"/>

<div class="span">

    <jsp:directive.include file="/jspf/left.jspf"/>

    <div class="middle">
        <c:if test="${message != null}">
            <h1>${message}</h1>
        </c:if>
        <c:if test="${errorMessage != null}">
            <h1>${errorMessage}</h1>
        </c:if>


        <form name="loginForm" method="POST" action="Controller"
              accept-charset="UTF-8">
            <input type="hidden" name="command" value="change_profile"/> <br/>
            <table class="loginForm">

                <tr>
                    <td class="loginForm1">
                        <fmt:message key="birthday"/>
                    </td>
                    <td class="loginForm2">
                        <input type="date" name="birthday" value="${sessionScope.user.userInf.birthday}"/>
                    </td>
                </tr>
                <tr>
                    <td class="loginForm1">
                        <fmt:message key="place_of_birth"/>
                    </td>
                    <td class="loginForm2">
                        <input type="text" name="place_of_birth" value="${sessionScope.user.userInf.clientAdditionalInformation.placeOfBirth}"/>
                    </td>
                </tr>
                <tr>
                    <td class="loginForm1">
                        <fmt:message key="city"/>
                    </td>
                    <td class="loginForm2">
                        <input type="text" name="city" value="${sessionScope.user.userInf.clientAdditionalInformation.city}"/>
                    </td>
                </tr>
                <tr>
                    <td class="loginForm1">
                        <fmt:message key="address"/>
                    </td>
                    <td class="loginForm2">
                        <input type="text" name="address" value="${sessionScope.user.userInf.clientAdditionalInformation.address}"/>
                    </td>
                </tr>
                <tr>
                    <td class="loginForm1">
                        <fmt:message key="home_phone"/>
                    </td>
                    <td class="loginForm2">
                        <input type="text" name="home_phone" value="${sessionScope.user.userInf.clientAdditionalInformation.homePhone}"/>
                    </td>
                </tr>
                <tr>
                    <td class="loginForm1">
                        <fmt:message key="mobile_phone"/>
                    </td>
                    <td class="loginForm2">
                        <input type="text" name="mobile_phone" value="${sessionScope.user.userInf.clientAdditionalInformation.mobilePhone}"/>
                    </td>
                </tr>
                <tr>
                    <td class="loginForm1">
                        <fmt:message key="email"/>
                    </td>
                    <td class="loginForm2">
                        <input type="text" name="email" value="${sessionScope.user.userInf.clientAdditionalInformation.email}"/>
                    </td>
                </tr>
                <tr>
                    <td class="loginForm1">
                        <fmt:message key="job"/>
                    </td>
                    <td class="loginForm2">
                        <input type="text" name="job" value="${sessionScope.user.userInf.clientAdditionalInformation.job}"/>
                    </td>
                </tr>
                <tr>
                    <td class="loginForm1">
                        <fmt:message key="position"/>
                    </td>
                    <td class="loginForm2">
                        <input type="text" name="position" value="${sessionScope.user.userInf.clientAdditionalInformation.position}"/>
                    </td>
                </tr>
                <tr>
                    <td class="loginForm1">
                        <fmt:message key="familystatus"/>
                    </td>
                    <td class="loginForm2">
                        <input type="text" name="familystatus" value="${sessionScope.user.userInf.clientAdditionalInformation.familyStatus}"/>
                    </td>
                </tr>
                <tr>
                    <td class="loginForm1">
                        <fmt:message key="nationality"/>
                    </td>
                    <td class="loginForm2">
                        <input type="text" name="nationality" value="${sessionScope.user.userInf.clientAdditionalInformation.nationality}"/>
                    </td>
                </tr>
                <tr>
                    <td class="loginForm1">
                        <fmt:message key="disability"/>
                    </td>
                    <td class="loginForm2">
                        <input type="text" name="disability" value="${sessionScope.user.userInf.clientAdditionalInformation.disability}"/>
                    </td>
                </tr>
                <tr>
                    <td class="loginForm1">
                        <fmt:message key="pensioner"/>
                    </td>
                    <td class="loginForm2">
                        <input type="text" name="pensioner" value="${sessionScope.user.userInf.clientAdditionalInformation.pensioner}"/>
                    </td>
                </tr>
                <tr>
                    <td class="loginForm1">
                        <fmt:message key="income"/>
                    </td>
                    <td class="loginForm2">
                        <input type="text" name="income" value="${sessionScope.user.userInf.clientAdditionalInformation.income}"/>
                    </td>
                </tr>
                <tr>
                    <td class="loginForm1">
                        <fmt:message key="reservist"/>
                    </td>
                    <td class="loginForm2">
                        <input type="text" name="reservist" value="${sessionScope.user.userInf.clientAdditionalInformation.reservist}"/>
                    </td>
                </tr>
                <tr>
                    <td class="loginForm1">
                        <fmt:message key="series"/>
                    </td>
                    <td class="loginForm2">
                        <input type="text" name="series" value="${sessionScope.user.userInf.passportData.series}"/>
                    </td>
                </tr>
                <tr>
                    <td class="loginForm1">
                        <fmt:message key="passno"/>
                    </td>
                    <td class="loginForm2">
                        <input type="text" name="passno" value="${sessionScope.user.userInf.passportData.no}"/>
                    </td>
                </tr>
                <tr>
                    <td class="loginForm1">
                        <fmt:message key="place_of_issue"/>
                    </td>
                    <td class="loginForm2">
                        <input type="text" name="place_of_issue" value="${sessionScope.user.userInf.passportData.placeOfIssue}"/>
                    </td>
                </tr>
                <tr>
                    <td class="loginForm1">
                        <fmt:message key="date_of_issue"/>
                    </td>
                    <td class="loginForm2">
                        <input type="date" name="date_of_issue" value="${sessionScope.user.userInf.passportData.dateOfIssue}"/>
                    </td>
                </tr>
                <tr>
                    <td class="loginForm1">
                        <fmt:message key="identification_no"/>
                    </td>
                    <td class="loginForm2">
                        <input type="text" name="identification_no" value="${sessionScope.user.userInf.passportData.identificationNo}"/>
                    </td>
                </tr>

                <tr>
                    <td class="loginForm1">
                        <fmt:message key="first_name"/>
                    </td>
                    <td class="loginForm2">
                        <input type="text" name="first_name" value="${sessionScope.user.firstName}"/>
                    </td>
                </tr>

                <tr>
                    <td class="loginForm1">
                        <fmt:message key="last_name"/>
                    </td>
                    <td class="loginForm2">
                        <input type="text" name="last_name" value="${sessionScope.user.lastName}"/>
                    </td>
                </tr>

                <tr>
                    <td class="loginForm1">
                        <fmt:message key="login"/>
                    </td>
                    <td class="loginForm2">
                        <input type="text" name="login" value="${sessionScope.user.login}"/>
                    </td>
                </tr>

                <tr>
                    <td colspan="2" align="center"><c:set var="submit">
                        <fmt:message key="submit"/>
                    </c:set> <input type="submit" name="SUBMIT" value="${submit}"/></td>
                </tr>
            </table>
        </form>
    </div>
</div>
<div style="clear: both;"></div>
<div>
    <jsp:directive.include file="/jspf/footer.jspf"/>
</div>
</body>
</html>
