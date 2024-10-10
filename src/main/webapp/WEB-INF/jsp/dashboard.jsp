<%@ page language="java" contentType="text/html; charset=UTF-8" pageEncoding="UTF-8"%>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<!DOCTYPE html>
<html lang="en">
<head>
    <meta charset="UTF-8">
    <meta name="viewport" content="width=device-width, initial-scale=1.0">
    <title>Pollar - Dashboard</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://kit.fontawesome.com/your-fontawesome-kit.js" crossorigin="anonymous"></script>
</head>
<body class="bg-gray-100">
<nav class="bg-white shadow-lg">
    <div class="max-w-6xl mx-auto px-4">
        <div class="flex justify-between">
            <div class="flex space-x-7">
                <div>
                    <a href="#" class="flex items-center py-4 px-2">
                        <span class="font-semibold text-gray-500 text-lg">P<img src="${pageContext.request.contextPath}/logo.png" alt="logo" class="inline w-3">llar</span>
                    </a>
                </div>
            </div>
            <div class="hidden md:flex items-center space-x-3 ">
                <a href="${pageContext.request.contextPath}/auth/logout" class="py-2 px-2 font-medium text-gray-500 rounded hover:bg-blue-500 hover:text-white transition duration-300">Log Out</a>
            </div>
        </div>
    </div>
</nav>

<div class="container mx-auto mt-8 px-4">
    <h1 class="text-2xl font-bold mb-4">Welcome, ${sessionScope.user.username}!</h1>

    <div class="bg-white shadow-md rounded px-8 pt-6 pb-8 mb-4">
        <h2 class="text-xl font-semibold mb-4">Your Dashboard</h2>

        <!-- Add your dashboard content here -->
        <div class="grid grid-cols-1 md:grid-cols-2 gap-4">
            <div class="bg-blue-100 p-4 rounded">
                <h3 class="font-bold">Recent Activities</h3>
                <!-- Add recent activities list -->
            </div>
            <div class="bg-green-100 p-4 rounded">
                <h3 class="font-bold">Statistics</h3>
                <!-- Add user statistics -->
            </div>
        </div>

        <!-- Add more sections as needed -->
    </div>
</div>

<footer class="bg-white text-center p-4 mt-8">
    <p>&copy; 2024 Pollar. All rights reserved.</p>
</footer>
</body>
</html>