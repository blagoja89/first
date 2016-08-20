<%@ page contentType="text/html;charset=UTF-8" language="java"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<html>
<head>
<title>Error page</title>
</head>
<body>
	<h1>An error was occurred!</h1>
	<h2>
	<div>Enter correct data!</div>
		<a href="/news/newsAction.do?method=list">To main page</a>
	</h2>
	<html:errors />
</body>
</html>