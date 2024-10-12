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
    <button class="bg-blue-500 text-white px-4 py-2 rounded" onclick="showForm()">Add Article</button>

    <!-- Add Article Form -->
    <div id="addArticleForm" class="mt-4 p-4 border rounded bg-gray-100" style="display: none;">
        <h3 class="text-lg font-bold mb-4">Add New Article</h3>
        <form action="${pageContext.request.contextPath}/article" method="post">
            <div class="mb-4">
                <label for="title" class="block text-gray-700">Title:</label>
                <input type="text" id="title" name="title" class="w-full border px-2 py-1" required>
            </div>
            <div class="mb-4">
                <label for="content" class="block text-gray-700">Content:</label>
                <textarea id="content" name="content" class="w-full border px-2 py-1" required></textarea>
            </div>
            <div class="mb-4">
                <label for="createdAt" class="block text-gray-700">Created At (yyyy-mm-dd):</label>
                <input type="date" id="createdAt" name="createdAt" class="w-full border px-2 py-1" required>
            </div>
            <div class="mb-4">
                <label for="lunchedAt" class="block text-gray-700">Lunched At (yyyy-mm-dd):</label>
                <input type="date" id="lunchedAt" name="lunchedAt" class="w-full border px-2 py-1">
            </div>
            <div class="mb-4">
                <label for="status" class="block text-gray-700">Status:</label>
                <select id="status" name="status" class="w-full border px-2 py-1">
                    <option value="DRAFT">Draft</option>
                    <option value="PUBLISHED">Published</option>
                </select>
            </div>
            <div class="mb-4">
                <label for="category" class="block text-gray-700">Category ID:</label>
                <input type="text" id="category" name="categoryId" class="w-full border px-2 py-1" required>
            </div>
            <div class="mb-4">
                <label for="user" class="block text-gray-700">User ID:</label>
                <input type="text" id="user" name="userId" class="w-full border px-2 py-1">
            </div>
            <div>
                <button type="submit" class="bg-green-500 text-white px-4 py-2 rounded">Submit</button>
                <button type="button" class="bg-red-500 text-white px-4 py-2 rounded" onclick="hideForm()">Cancel</button>
            </div>
        </form>
    </div>

    <!-- Update Article Modal -->
    <div id="updateArticleModal" class="fixed inset-0 flex items-center justify-center bg-gray-800 bg-opacity-50 hidden">
        <div class="bg-white p-6 rounded shadow-lg w-96">
            <h3 class="text-lg font-bold mb-4">Update Article</h3>
            <form id="updateArticleForm" action="${pageContext.request.contextPath}/article?action=update" method="post">
                <input type="hidden" name="id" id="updateArticleId">
                <div class="mb-4">
                    <label for="updateTitle" class="block text-gray-700">Title:</label>
                    <input type="text" id="updateTitle" name="title" class="w-full border px-2 py-1" required>
                </div>
                <div class="mb-4">
                    <label for="updateContent" class="block text-gray-700">Content:</label>
                    <textarea id="updateContent" name="content" class="w-full border px-2 py-1" required></textarea>
                </div>
                <div class="mb-4">
                    <label for="updateCreatedAt" class="block text-gray-700">Created At:</label>
                    <input type="date" id="updateCreatedAt" name="createdAt" class="w-full border px-2 py-1" required>
                </div>
                <div class="mb-4">
                    <label for="updateLunchedAt" class="block text-gray-700">Lunched At:</label>
                    <input type="date" id="updateLunchedAt" name="lunchedAt" class="w-full border px-2 py-1">
                </div>
                <div class="mb-4">
                    <label for="updateStatus" class="block text-gray-700">Status:</label>
                    <select id="updateStatus" name="status" class="w-full border px-2 py-1">
                        <option value="DRAFT">Draft</option>
                        <option value="PUBLISHED">Published</option>
                    </select>
                </div>
                <div>
                    <button type="submit" class="bg-green-500 text-white px-4 py-2 rounded">Update</button>
                    <button type="button" class="bg-red-500 text-white px-4 py-2 rounded" onclick="hideUpdateModal()">Cancel</button>
                </div>
            </form>
        </div>
    </div>


    <table class="table-auto w-full mt-8">
        <thead>
        <tr>
            <th class="px-4 py-2">ID</th>
            <th class="px-4 py-2">Title</th>
            <th class="px-4 py-2">Content</th>
            <th class="px-4 py-2">Created At</th>
            <th class="px-4 py-2">Lunched At</th>
            <th class="px-4 py-2">Status</th>
            <th class="px-4 py-2">Category</th>
            <th class="px-4 py-2">Actions</th>
        </tr>
        </thead>
        <tbody>
        <c:forEach var="article" items="${articles}">
            <tr>
                <td class="border px-4 py-2">${article.id}</td>
                <td class="border px-4 py-2">${article.title}</td>
                <td class="border px-4 py-2">${article.content}</td>
                <td class="border px-4 py-2">${article.createdAt}</td>
                <td class="border px-4 py-2">${article.lunchedAt}</td>
                <td class="border px-4 py-2">${article.status}</td>
                <td class="border px-4 py-2">${article.category.id}</td>
                <td class="border px-4 py-2">
                    <button type="button" class="text-green-500"
                            onclick="showUpdateModal('${article.id}', '${article.title}', '${article.content}', '${article.createdAt}', '${article.lunchedAt}', '${article.status}')">
                        Update
                    </button>
                    <form action="${pageContext.request.contextPath}/article?action=delete&id=${article.id}" method="post"
                          onsubmit="return confirm('Are you sure you want to delete this article?');" class="mt-1">
                        <button type="submit" class="text-red-500">Delete</button>
                    </form>
                </td>

            </tr>
        </c:forEach>
        </tbody>
    </table>

</main>

<script>
    function showForm() {
        document.getElementById("addArticleForm").style.display = "block";
    }

    function hideForm() {
        document.getElementById("addArticleForm").style.display = "none";
    }

    function showUpdateModal(id, title, content, createdAt, lunchedAt, status) {
        document.getElementById("updateArticleId").value = id;
        document.getElementById("updateTitle").value = title;
        document.getElementById("updateContent").value = content;
        document.getElementById("updateCreatedAt").value = createdAt;
        document.getElementById("updateLunchedAt").value = lunchedAt;
        document.getElementById("updateStatus").value = status;

        document.getElementById("updateArticleModal").classList.remove("hidden");
    }

    function hideUpdateModal() {
        document.getElementById("updateArticleModal").classList.add("hidden");
    }

</script>

</body>
</html>
