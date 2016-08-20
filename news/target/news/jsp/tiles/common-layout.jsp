<%@taglib uri="http://struts.apache.org/tags-tiles" prefix="tiles"%>
<%@taglib uri="http://struts.apache.org/tags-html" prefix="html"%>
<%@taglib uri="http://struts.apache.org/tags-bean" prefix="bean"%>
<%@taglib prefix="c" uri="http://java.sun.com/jsp/jstl/core"%>
<%@taglib prefix="fmt" uri="http://java.sun.com/jsp/jstl/fmt"%>

<!DOCTYPE html>
<html lang="en">
<head>
<link href="css/style.css" rel="stylesheet">
<meta charset="utf-8">
<title><bean:message key="title" /></title>
<meta name="viewport" content="width=device-width, initial-scale=1.0">
<meta name="description" content="">
<meta name="author" content="">



<script src="js/jquery-1.9.0.js"></script>
<script src="js/jquery.validate.min.js"></script>
<script src="js/moment.min.js"></script>
<script src="js/footer.js"></script>
</head>

<body>
	<tiles:insert attribute="header" />

	<div class="layout">

		<tiles:insert attribute="menu" />

		<div class="content">
			<tiles:insert attribute="body" />
		</div>

	</div>
	<tiles:insert attribute="footer" />


</body>
</html>