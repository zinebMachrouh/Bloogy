<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pollar - Error</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://kit.fontawesome.com/your-fontawesome-kit.js" crossorigin="anonymous"></script>
</head>
<body class="bg-gray-100 min-h-screen flex items-center justify-center">
<div id="app" class="w-full max-w-md">
    <div class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
        <h2 class="text-2xl font-bold mb-6 text-center">P<img src="${pageContext.request.contextPath}/logo.png" alt="logo" class="inline w-3"> llar</h2>
        <p class="text-center text-lg mb-4 text-red-500">
            An error occurred: <%= request.getAttribute("error") %>
        </p>
        <div class="text-center">
            <a href="${pageContext.request.contextPath}/auth/login" class="font-medium text-blue-600 hover:text-blue-500">Back to Login</a>
        </div>
    </div>
</div>
</body>
</html>