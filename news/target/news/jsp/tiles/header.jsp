<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>

<div class="header">
	<html:link action="/newsAction.do?method=list">
		<span id="headtitle"> <bean:message key="title" />
		</span>
	</html:link>
	<div id="languagebar">
		<html:link action="/Locale.do?locale=en">
			<bean:message key="english" />
		</html:link>
		<html:link action="/Locale.do?locale=ru">
			<bean:message key="russian" />
		</html:link>
	</div>
</div>