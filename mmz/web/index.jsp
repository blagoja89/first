<jsp:root xmlns:jsp="http://java.sun.com/JSP/Page"
          xmlns:c="http://java.sun.com/jsp/jstl/core" version="2.0"
          xmlns:fmt="http://java.sun.com/jsp/jstl/fmt">
  <fmt:setBundle basename="property.messages" />
  <jsp:directive.page contentType="text/html; charset=UTF-8" />
  <html>
  <head>
    <link rel="stylesheet" type="text/css" href="css/style.css" />

  </head>
  <body>
  <jsp:forward page="jsp/home.jsp" />
  </body>
  </html>
</jsp:root>