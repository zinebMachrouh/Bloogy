<%@ page contentType="text/html;charset=UTF-8" language="java" %>
<%@ taglib uri="http://java.sun.com/jsp/jstl/core" prefix="c" %>
<html>
<head>
    <title>Article Main</title>
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

<section class="flex mx-auto px-20">
    <!-- Main Article Section -->
    <div class="flex-col lg:w-3/4 ">
        <div class="w-full  p-4">
            <div class="transition-all duration-150 flex flex-col bg-white rounded-lg" style="box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
">
                <img src="https://www.unfe.org/wp-content/uploads/2019/04/SM-placeholder-1024x512.png" alt="Article Image" class="object-cover w-full h-56 rounded-t-lg">
                <div class="p-4">
                    <h2 class="text-2xl font-bold">How to Yawn in 7 Days</h2>
                    <p class="mt-2 text-gray-700">Lorem ipsum dolor sit amet consectetur adipisicing elit...</p>
                    <div class="flex items-center mt-4">
                        <span class="text-xs text-gray-500">Web Programming</span>
                        <span class="text-xs text-gray-500 mx-2">1.5k Views</span>
                        <span class="text-xs text-gray-500 mx-2">25 Comments</span>
                        <span class="text-xs text-gray-500 mx-2">7 Shares</span>
                    </div>
                </div>
            </div>
        </div>

        <!-- Secondary Article Section Container -->
        <c:if test="${not empty articles}">
            <div class="flex justify-between flex-wrap">
                <c:forEach var="article" items="${articles}">
                    <!-- Secondary Article Section -->
                    <div class="transition-all duration-150 w-full px-4 md:w-1/2 mb-6">
                        <div class="flex flex-col items-stretch min-h-full pb-4 mb-6 transition-all duration-150 bg-white rounded-lg" style="box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;">
                            <div class="md:flex-shrink-0">
                                <img src="https://www.unfe.org/wp-content/uploads/2019/04/SM-placeholder-1024x512.png" alt="Blog Cover" class="object-fill w-full rounded-lg rounded-b-none md:h-56"/>
                            </div>
                            <div class="flex items-center justify-between px-4 py-2 overflow-hidden">
                                <span class="text-xs font-medium text-blue-600 uppercase">${article.category.name}</span>
                                <div class="flex flex-row items-center">
                                    <div class="text-xs font-medium text-gray-500 flex flex-row items-center mr-2">
                                        <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M15 12a3 3 0 11-6 0 3 3 0 016 0z"></path>
                                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M2.458 12C3.732 7.943 7.523 5 12 5c4.478 0 8.268 2.943 9.542 7-1.274 4.057-5.064 7-9.542 7-4.477 0-8.268-2.943-9.542-7z"></path>
                                        </svg>
                                        <span>1.5k</span>
                                    </div>

                                    <div class="text-xs font-medium text-gray-500 flex flex-row items-center mr-2">
                                        <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M7 8h10M7 12h4m1 8l-4-4H5a2 2 0 01-2-2V6a2 2 0 012-2h14a2 2 0 012 2v8a2 2 0 01-2 2h-3l-4 4z"></path>
                                        </svg>
                                        <span>25</span>
                                    </div>

                                    <div class="text-xs font-medium text-gray-500 flex flex-row items-center">
                                        <svg class="w-4 h-4 mr-1" fill="none" stroke="currentColor" viewBox="0 0 24 24" xmlns="http://www.w3.org/2000/svg">
                                            <path stroke-linecap="round" stroke-linejoin="round" stroke-width="2" d="M14 10h4.764a2 2 0 011.789 2.894l-3.5 7A2 2 0 0115.263 21h-4.017c-.163 0-.326-.02-.485-.06L7 20m7-10V5a2 2 0 00-2-2h-.095c-.5 0-.905.405-.905.905 0 .714-.211 1.412-.608 2.006L7 11v9m7-10h-2M7 20H5a2 2 0 01-2-2v-6a2 2 0 012-2h2.5"></path>
                                        </svg>
                                        <span>7</span>
                                    </div>
                                </div>
                            </div>
                            <hr class="border-gray-300" />
                            <div class="flex flex-wrap items-center flex-1 px-4 py-1 text-center mx-auto">
                                <a href="article?action=view&id=${article.id}" class="hover:underline">
                                    <h2 class="text-2xl font-bold tracking-normal text-gray-800">
                                            ${article.title}
                                    </h2>
                                </a>
                            </div>
                            <hr class="border-gray-300" />
                            <p class="flex flex-row flex-wrap w-full px-4 py-2 overflow-hidden text-sm text-justify text-gray-700">
                                    ${article.content}
                            </p>
                            <hr class="border-gray-300" />
                            <section class="px-4 py-2 mt-2">
                                <div class="flex items-center justify-between">
                                    <div class="flex items-center flex-1">
                                        <img class="object-cover h-10 rounded-full" src="https://thumbs.dreamstime.com/b/default-avatar-photo-placeholder-profile-icon-eps-file-easy-to-edit-default-avatar-photo-placeholder-profile-icon-124557887.jpg" alt="Avatar"/>
                                        <div class="flex flex-col mx-2">
                                            <a href="" class="font-semibold text-gray-700 hover:underline">
                                                    ${article.user.username}
                                            </a>
                                            <span class="mx-1 text-xs text-gray-600">${article.lunchedAt}</span>
                                        </div>
                                    </div>
                                    <p class="mt-1 text-xs text-gray-600">9 minutes read</p>
                                </div>
                            </section>
                        </div>
                    </div>
                </c:forEach>
            </div>
        </c:if>
        <c:if test="${empty articles}">
            <p>No articles available.</p>
        </c:if>


        </div>
    </div>

    <div class="w-full lg:w-1/4 flex-direction-column  pt-4" style="margin-bottom: 13px ; justify-content: space-between;">
        <!-- Trending Articles Section -->
        <div style="height: 59% !important;" >
            <div class="bg-white rounded-lg p-4 h-full" style="box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;
">
                <h3 class="font-bold text-lg" style="color: #0096C7 ">Trending Articles</h3>

                <ul>
                    <li class="mb-2">
                        <a href="#" class="hover:underline">Article Title 1</a>
                        <span class="text-xs text-gray-500">6 Comments</span>
                    </li>
                    <li class="mb-2">
                        <a href="#" class="hover:underline">Article Title 2</a>
                        <span class="text-xs text-gray-500">4 Comments</span>
                    </li>
                    <li class="mb-2">
                        <a href="#" class="hover:underline">Article Title 3</a>
                        <span class="text-xs text-gray-500">3 Comments</span>
                    </li>
                </ul>
            </div>
        </div>

        <!-- Categories Section -->
        <div style="height: 41% !important;">
            <div class="bg-white rounded-lg p-4 mt-2 h-full" style="box-shadow: rgba(99, 99, 99, 0.2) 0px 2px 8px 0px;">
                <h3 class="font-bold text-lg" style="color: #0096C7">Trending Categories</h3>

                <ul>
                    <li class="mb-2">
                        <a href="#" class="hover:underline">Category Title 1</a>
                        <span class="text-xs text-gray-500">5 Articles</span>
                    </li>
                    <li class="mb-2">
                        <a href="#" class="hover:underline">Category Title 2</a>
                        <span class="text-xs text-gray-500">4 Articles</span>
                    </li>
                    <li class="mb-2">
                        <a href="#" class="hover:underline">Category Title 3</a>
                        <span class="text-xs text-gray-500">3 Articles</span>
                    </li>
                </ul>
            </div>
        </div>
    </div>
</section>

</body>
</html>