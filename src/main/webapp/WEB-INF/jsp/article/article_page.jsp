<%@ page import="dto.ArticleDTO" %><%--
  Created by IntelliJ IDEA.
  User: rayane
  Date: 10/11/2024
  Time: 7:27 PM
  To change this template use File | Settings | File Templates.
--%>
<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<html>
<head>
    <title>Article</title>
    <script src="https://cdn.tailwindcss.com"></script>
    <script src="https://kit.fontawesome.com/6e1faf1eda.js" crossorigin="anonymous"></script>
    <style>
        body{
            background-color: #FAFAFA;
        }
        .comments{
            .comment{
                padding: 10px 0;
            }
        }
    </style>
</head>
<body>
<nav class="flex justify-between py-5 items-center" style="padding: 20px 95px; background-color: #FAFAFA">
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
<main class="px-20 mt-8 w-full mx-auto" style="height: 85vh; background-color: #FAFAFA">
    <div class="w-full flex flex-wrap justify-between h-full">
        <div class="w-4/6 px-4 mb-8">
            <%-- Retrieve the article from the request --%>
            <%
                ArticleDTO article = (ArticleDTO) request.getAttribute("article");
                if (article != null) {
            %>
            <img src="https://images.squarespace-cdn.com/content/v1/54822a56e4b0b30bd821480c/45ed8ecf-0bb2-4e34-8fcf-624db47c43c8/Golden+Retrievers+dans+pet+care.jpeg" alt="Featured Image" class="w-full h-64 object-cover rounded bg-red">
            <h2 class="text-4xl font-bold mt-4 mb-2"><%= article.getTitle() %></h2>
            <p class="text-gray-700 mb-4"><%= article.getContent() %></p>
            <p class="text-gray-700 mb-4">Published on: <%= article.getCreatedAt() %></p>
            <p class="text-gray-700 mb-4">Status: <%= article.getStatus() %></p>
            <%
            } else {
            %>
            <p class="text-red-500">Article not found.</p>
            <%
                }
            %>
        </div>
        <div class="w-2/6  px-4 mb-8 h-100" >
            <div class="px-4 py-2 rounded h-full" style="box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px; background-color: #fff">
                <h3 class="text-lg font-bold mb-2" style="color: #0096C7; font-size: 26px;height: 5%">Comments</h3>
                <div class="comments" style="height: 85%; overflow: auto;">
                    <div class="comment">
                        <div class="flex align-center" style="gap: 5px;">
                            <p class="font-semibold">John Doe</p>
                            <p class="text-gray-500">12/12/2021</p>
                        </div>
                        <p class="text-gray-700">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec purus feugiat,kfjdfhfduk</p>
                    </div><div class="comment">
                        <div class="flex align-center" style="gap: 5px;">
                            <p class="font-semibold">John Doe</p>
                            <p class="text-gray-500">12/12/2021</p>
                        </div>
                        <p class="text-gray-700">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec purus feugiat,kfjdfhfduk</p>
                    </div><div class="comment">
                        <div class="flex align-center" style="gap: 5px;">
                            <p class="font-semibold">John Doe</p>
                            <p class="text-gray-500">12/12/2021</p>
                        </div>
                        <p class="text-gray-700">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec purus feugiat,kfjdfhfduk</p>
                    </div><div class="comment">
                        <div class="flex align-center" style="gap: 5px;">
                            <p class="font-semibold">John Doe</p>
                            <p class="text-gray-500">12/12/2021</p>
                        </div>
                        <p class="text-gray-700">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec purus feugiat,kfjdfhfduk</p>
                    </div><div class="comment">
                        <div class="flex align-center" style="gap: 5px;">
                            <p class="font-semibold">John Doe</p>
                            <p class="text-gray-500">12/12/2021</p>
                        </div>
                        <p class="text-gray-700">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec purus feugiat,kfjdfhfduk</p>
                    </div><div class="comment">
                        <div class="flex align-center" style="gap: 5px;">
                            <p class="font-semibold">John Doe</p>
                            <p class="text-gray-500">12/12/2021</p>
                        </div>
                        <p class="text-gray-700">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec purus feugiat,kfjdfhfduk</p>
                    </div><div class="comment">
                        <div class="flex align-center" style="gap: 5px;">
                            <p class="font-semibold">John Doe</p>
                            <p class="text-gray-500">12/12/2021</p>
                        </div>
                        <p class="text-gray-700">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec purus feugiat,kfjdfhfduk</p>
                    </div><div class="comment">
                        <div class="flex align-center" style="gap: 5px;">
                            <p class="font-semibold">John Doe</p>
                            <p class="text-gray-500">12/12/2021</p>
                        </div>
                        <p class="text-gray-700">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec purus feugiat,kfjdfhfduk</p>
                    </div><div class="comment">
                        <div class="flex align-center" style="gap: 5px;">
                            <p class="font-semibold">John Doe</p>
                            <p class="text-gray-500">12/12/2021</p>
                        </div>
                        <p class="text-gray-700">Lorem ipsum dolor sit amet, consectetur adipiscing elit. Nulla nec purus feugiat,kfjdfhfduk</p>
                    </div>
                </div>
                <div style="height: 7.5%;" class="flex flex-col justify-center align-center">
                    <form method="post" class="my-auto" style="background-color: #FFC636; height: 90%; background-color: #fff; border-top: solid #e0dfdf 1px;">
                        <input type="text" name="comment" id="comment" placeholder="Share your thoughts <3" class="h-full" style="width: 90%; padding: 0 10px; border: none; outline: none">
                        <button type="submit" class="" style="color: #BDC3C7"><i class="fa-regular fa-paper-plane" style="width: 10%; margin: auto"></i></button>
                    </form>
                </div>
            </div>
        </div>
    </div>
</main>
</body>
</html>
