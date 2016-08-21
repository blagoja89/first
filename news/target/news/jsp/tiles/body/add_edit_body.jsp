<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<link href="css/edit_news.css" rel="stylesheet">


<div class="breadcrumb">
	<a href="/news/newsAction.do?method=list"><bean:message
			key="news.list" /></a>>>
	<bean:message key="add.news" />
</div>

<div>
	<c:if test="${errorMessage != null}">
		<h1>${errorMessage}</h1>
	</c:if>
</div>

<div>

	<html:form action="/newsAction.do?method=save" styleId="newsForm">
		<html:hidden property="news.id" name="newsForm"
			value="${newsForm.news.id}" />


		<%-- title--%>
		<div>
			<label class="lab" for="title"> <bean:message
					key="news.title" /></label>
			<div class="cont">
				<%-- title input --%>
				<html:text property="news.title" styleId="title" name="title"
					styleClass="required" maxlength="100"
					value="${newsForm.news.title}"></html:text>
			</div>
		</div>


		<%-- date--%>
		<div>
			<jsp:useBean id="today" class="java.util.Date" />
			<fmt:formatDate value="${today}" pattern="MM/dd/yyyy" var="newsDate" />

			<c:if test="${newsForm.news.date ne null}">
				<fmt:formatDate value="${newsForm.news.date}" pattern="MM/dd/yyyy"
					var="newsDate" />
			</c:if>

			<label class="lab" for="newsDate"><bean:message
					key="news.date" /></label>

			<div class="cont">
				<%-- date input --%>
				<html:text property="news.date" styleId="date" name="date"
					styleClass="required" value="${newsDate}"></html:text>
			</div>
		</div>

		<%-- brief --%>
		<div>
			<label class="lab" for="brief"><bean:message key="brief" /></label>

			<div class="cont">
				<%-- brief text area --%>
				<html:textarea property="news.brief" styleId="brief" name="brief"
					styleClass="required" value="${newsForm.news.brief}" rows="5" />
			</div>
		</div>

		<%-- content --%>
		<div>
			<label class="lab" for="content"><bean:message key="content" /></label>

			<div class="cont">
				<%-- content text area --%>
				<html:textarea property="news.content" styleId="content"
					name="content" styleClass="required"
					value="${newsForm.news.content}" rows="10" />
			</div>
		</div>

		<div>
			<div id="buttonBar">
				<html:submit styleClass="myButton"
					onclick="return newsValidation();">
					<bean:message key="save" />
				</html:submit>
				<button class="myButton"
					onclick="javascript:history.back(); return false;">
					<bean:message key="cancel" />
				</button>
			</div>
		</div>

	</html:form>
</div>


<script src="js/edit_rules.js"></script>

<script src="js/messages_ru_js.jsp"></script>




