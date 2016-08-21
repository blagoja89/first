<%@taglib uri="http://struts.apache.org/tags-html" prefix="html" %>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean" %>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt" %>
<link href="css/view_news.css" rel="stylesheet">

<div>

    <div class="breadcrumb"><a href="/news/newsAction.do?method=list"><bean:message
            key="news"/></a>>><bean:message
            key="news.view"/></div>

    <div>
        <label class="lab"><bean:message key="news.title"/></label>

        <div class="cont">
            <label>${newsForm.news.title}</label>
        </div>
    </div>
    <br>

    <div>
        <label class="lab"><bean:message key="news.date"/></label>

        <div class="cont">
            <label><fmt:formatDate value="${newsForm.news.date}" pattern="dd/MM/yyyy"/></label>
        </div>
    </div>
    <br>

    <div>
        <label class="lab"><bean:message key="brief"/></label>

        <div class="cont">
            <label> ${newsForm.news.brief}</label>
        </div>
    </div>
    <br>

    <div>
        <label class="lab"><bean:message key="content"/></label>

        <div class="cont">
            <label>${newsForm.news.content}</label>
        </div>
    </div>
    <br>

    <div id="buttonsBar">
        <bean:define id="id" property="news.id" value="${newsForm.news.id}"/>
        <html:form action="/newsAction.do?method=delete">
            <html:hidden property="newsSelected" value="${newsForm.news.id}"/>
            <html:submit onclick="return areyousure();" styleClass="myButton"><bean:message key="delete"/></html:submit>
        </html:form>
        <html:link action="/newsAction.do?method=edit"  paramId="news.id"
                   paramName="id">
            <button class="myButton"><bean:message key="edit"/></button>
        </html:link>
    </div>
</div>
<script src="js/check_news_selected_js.jsp"></script>