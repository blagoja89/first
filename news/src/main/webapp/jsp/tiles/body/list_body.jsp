<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-logic" prefix="logic"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@ taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<%@ page language="java" contentType="text/html; charset=UTF-8"
	pageEncoding="UTF-8"%>

<link href="css/news_list.css" rel="stylesheet">


<div class="breadcrumb"><a href="/news/newsAction.do?method=list">
<bean:message key="news" /></a>>><bean:message key="news.list" />
</div>


<div>

	<html:form action="/newsAction.do?method=delete" styleId="newsForm">
		<c:forEach items="${newsForm.newsList}" var="news">
			<div class="newsBlock">
				<span>
					<h2 class="title">
						<label>${news.title}</label>
					</h2> <span class="date"> <label><fmt:formatDate
								value="${news.date}" pattern="dd/MM/yyyy" /></label>
				</span>
				</span>

				<p class="brief">
					<label>${news.brief}</label>
				</p>

				<div class="buttonBar">
					<bean:define id="id" property="news.id" value="${news.id}" />
					<html:link action="/newsAction.do?method=view"
						paramId="news.id" paramName="id">
						<bean:message key="view" />
					</html:link>
					<html:link action="/newsAction.do?method=edit"
						paramId="news.id" paramName="id">
						<bean:message key="edit" />
					</html:link>
					<html:multibox property="newsSelected" value="${news.id}" />
				</div>
			</div>
		</c:forEach>
		<div id="deleteBtn">
			<html:submit onclick="return checknewsSelected();"
				styleClass="myButton">
				<bean:message key="delete" />
			</html:submit>
		</div>
	</html:form>
</div>
<script src="js/check_news_selected_js.jsp"></script>