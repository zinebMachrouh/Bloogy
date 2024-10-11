<%--
  Created by IntelliJ IDEA.
  User: rayane
  Date: 10/11/2024
  Time: 7:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Article Page</title>
    <script src="https://cdn.tailwindcss.com"></script>
</head>
<body>
<nav class="flex justify-between py-5 items-center bg-white" style="padding: 25px 120px">
    <h1 class="text-xl text-gray-800 font-bold">Bloogy</h1>
    <div class="flex items-center gap-8">
        <div class="flex items-center border-2 border-black rounded">
            <svg xmlns="http://www.w3.org/2000/svg" class="h-5 w-5 pt-0.5 text-gray-600" fill="none" viewBox="0 0 24 24" stroke="currentColor">
                <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M21 21l-6-6m2-5a7 7 0 11-14 0 7 7 0 0114 0z"></path>
            </svg>
            <input class="ml-2 outline-none bg-transparent" type="text" name="search" id="search" placeholder="Search...">
        </div>
        <ul class="flex items-center space-x-6">
            <li class="font-semibold text-gray-700">Home</li>
            <li class="font-semibold text-gray-700">Articles</li>
            <li>
                <svg xmlns="http://www.w3.org/2000/svg" fill="none" viewBox="0 0 24 24" stroke-width="1.5" stroke="currentColor" class="size-6">
                    <path stroke-linecap="round" stroke-linejoin="round" d="M17.982 18.725A7.488 7.488 0 0 0 12 15.75a7.488 7.488 0 0 0-5.982 2.975m11.963 0a9 9 0 1 0-11.963 0m11.963 0A8.966 8.966 0 0 1 12 21a8.966 8.966 0 0 1-5.982-2.275M15 9.75a3 3 0 1 1-6 0 3 3 0 0 1 6 0Z"></path>
                </svg>
            </li>
        </ul>
    </div>
</nav>
<main class="container mx-auto mt-8">
    <div class="flex flex-wrap justify-between">
        <div class="w-full md:w-8/12 px-4 mb-8">
            <img src="" alt="Featured Image" class="w-full h-64 object-cover rounded bg-red">
            <h2 class="text-4xl font-bold mt-4 mb-2">My First Blog Post</h2>
            <p class="text-gray-700 mb-4">Lorem ipsum dolor sit amet, consectetur adipiscing elit, sed do eiusmod tempor
                incididunt ut labore et dolore magna aliqua. Ut enim ad minim veniam, quis nostrud exercitation ullamco
                laboris nisi ut aliquip ex ea commodo consequat.</p>
            <p class="text-gray-700 mb-4">Duis aute irure dolor in reprehenderit in voluptate velit esse cillum dolore
                eu fugiat nulla pariatur. Excepteur sint occaecat cupidatat non proident, sunt in culpa qui officia
                deserunt mollit anim id est laborum.</p>
            <p class="text-gray-700 mb-4">Sed ut perspiciatis unde omnis iste natus error sit voluptatem accusantium
                doloremque laudantium, totam rem aperiam, eaque ipsa quae ab illo inventore veritatis et quasi
                architecto beatae vitae dicta sunt explicabo.</p>
        </div>
        <div class="w-full md:w-4/12 px-4 mb-8">
            <div class="bg-gray-100 px-4 py-6 rounded">
                <h3 class="text-lg font-bold mb-2">Categories</h3>
                <ul class="list-disc list-inside">
                    <li><a href="#" class="text-gray-700 hover:text-gray-900">Technology</a></li>
                    <li><a href="#" class="text-gray-700 hover:text-gray-900">Travel</a></li>
                    <li><a href="#" class="text-gray-700 hover:text-gray-900">Food</a></li>
                </ul>
            </div>
        </div>
    </div>
</main>
</body>
</html>
