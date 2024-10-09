<%@ tag language="java" pageEncoding="UTF-8" %>
<%@ attribute name="title" required="true" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <meta charset="UTF-8">
    <title><c:out value="${title}" /></title>
    <link rel="stylesheet" href="<c:url value="/css/style.css" />">
    <script src="<c:url value="/script/script.js" />" defer></script>
</head>
<body>

<main>
    <jsp:doBody />
</main>

</body>
</html>
