<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Article List</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<nav class="flex justify-between py-5 items-center bg-white" style="padding: 25px 120px">
    <h1 class="text-xl text-gray-800 font-bold">Bloogy</h1>
    <div class="flex items-center gap-8">
        <ul class="flex items-center space-x-6">
            <li class="font-semibold text-gray-700"><a href="home">Home</a></li>
            <li class="font-semibold text-gray-700"><a href="articles">Articles</a></li>
        </ul>
    </div>
</nav>
<main class="container mx-auto mt-8">
    <h2 class="text-2xl font-bold mb-6">List of Articles</h2>
    <table class="min-w-full bg-white border border-gray-200">
        <thead>
        <tr>
            <th class="py-2 px-4 border-b">ID</th>
            <th class="py-2 px-4 border-b">Title</th>
            <th class="py-2 px-4 border-b">Content</th>
            <th class="py-2 px-4 border-b">Published Date</th>
            <th class="py-2 px-4 border-b">Status</th>
            <th class="py-2 px-4 border-b">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:choose>
            <c:when test="${not empty articles}">
                <c:forEach var="article" items="${articles}">
                    <tr>
                        <td class="py-2 px-4 border-b">${article.id}</td>
                        <td class="py-2 px-4 border-b">${article.title}</td>
                        <td class="py-2 px-4 border-b">
                            <c:out value="${article.content}" escapeXml="true"/>
                            <c:if test="${article.content.length() > 50}">...</c:if>
                        </td>
                        <td class="py-2 px-4 border-b">${article.createdAt}</td>
                        <td class="py-2 px-4 border-b">${article.status}</td>
                        <td class="py-2 px-4 border-b">
                            <a href="article?action=view&id=${article.id}" class="text-blue-500 hover:underline">View</a>
                        </td>
                    </tr>
                </c:forEach>
            </c:when>
            <c:otherwise>
                <tr>
                    <td colspan="6" class="py-2 px-4 text-center">No articles found.</td>
                </tr>
            </c:otherwise>
        </c:choose>
        </tbody>
    </table>
</main>
</body>
</html>
