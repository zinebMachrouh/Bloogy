<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pollar - Reset Password</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://kit.fontawesome.com/your-fontawesome-kit.js" crossorigin="anonymous"></script>
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">
<div id="app" class="w-full max-w-md">
    <div class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
        <h2 class="text-2xl font-bold mb-6 text-center">P<img src="${pageContext.request.contextPath}/logo.png" alt="logo" class="inline w-3"> llar</h2>
        <form action="${pageContext.request.contextPath}/auth/reset-password" method="post" class="space-y-4">
            <input type="hidden" name="token" value="${param.token}">
            <div>
                <label for="new-password" class="block text-gray-700 text-sm font-bold mb-2">New Password</label>
                <input type="password" name="newPassword" id="new-password" required placeholder="Min : 8 characters"
                       class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
            </div>
            <div>
                <label for="confirm-password" class="block text-gray-700 text-sm font-bold mb-2">Confirm New Password</label>
                <input type="password" name="confirmPassword" id="confirm-password" required placeholder="Min : 8 characters"
                       class="shadow appearance-none border rounded w-full py-2 px-3 text-gray-700 leading-tight focus:outline-none focus:shadow-outline">
            </div>
            <p class="text-red-500 text-xs italic">
                <% if (request.getAttribute("error") != null) { %>
                <%= request.getAttribute("error") %>
                <% } %>
            </p>
            <button class="bg-blue-500 hover:bg-blue-700 text-white font-bold py-2 px-4 rounded focus:outline-none focus:shadow-outline w-full" type="submit">
                Reset Password
            </button>
        </form>
    </div>
</div>
</body>
</html>