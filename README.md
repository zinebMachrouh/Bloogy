# 🛠️ Welcome to Blogify! 🛠️

Hello, Content Management Enthusiasts! 👋

Welcome to **Blogify**, your advanced Java-based web application for collaborative content management. Designed for businesses seeking an interactive and participative solution for blog article management, **Blogify** is built using modern web technologies like **Java Servlets, JSP, JSTL**, and **Hibernate** to handle articles, comments, and authors with ease.

## 🚀 About Blogify

**Blogify** helps teams manage blog articles, comments, and authors through an intuitive web interface. It allows you to create and manage articles, assign comments, and track overall engagement through user interactions. The platform is built to meet the collaborative needs of modern teams with robust features for CRUD operations, real-time statistics, and user-friendly interfaces.

## 📁 Project Structure

Here's an overview of the project structure for **Blogify**:

- **config**: Configuration settings such as `DatabaseConnection`, Hibernate configuration, and session management.
- **controllers**: Contains controllers for handling HTTP requests and managing articles, comments, and authors.
- **dao**: Data Access Objects responsible for interacting with the database using Hibernate.
- **dto**: Data Transfer Objects to facilitate passing data between layers.
- **models**: Contains classes representing the core entities: `Article`, `Comment`, `Author`, and enums for comment types.
- **repositories**: Contains Hibernate-based repositories for querying and managing entities in the database.
- **services**: Business logic layer that manages operations related to articles, comments, and authors.
- **utils**: Utility classes that handle additional helper functions and input validation.
- **webapp**: Contains JSP pages and static resources (CSS, JavaScript) for rendering the UI.
- **resources**: Stores configuration files such as `app.properties` and database setup scripts.

## 🧩 Key Features

- **Article Management**: Create, view, and manage articles with essential details such as title, content, status, and statistics on comments.
- **Comment Management**: Add, update, and manage comments assigned to each article, including approval statuses.
- **Author Management**: Manage authors with their roles (Contributor, Editor) and oversee their contributions.
- **Pagination**: View articles, comments, and authors with paginated results.
- **Search**: Search for specific articles by title.

## 🌐 Web Application Pages

### Article Management Page
- **Article Listing with Pagination**: Browse articles and view their statuses.
- **Create, Update, Delete Articles**: Manage article details and life cycles.
- **Search**: Search for specific articles by title.
- **Statistics**: Display the number of comments for each article.

### Comment Management Page
- **Comment Listing by Article with Pagination**: Manage comments for each article.
- **Add, Update, Delete Comments**: Handle comments and assign approval status (Approved/Rejected).

### Author Management Page
- **Author Listing with Pagination**: Manage authors and their roles.
- **Add, Update, Delete Authors**: Handle author details and contributions.

## 🎨 Class Structure Overview

- **Article**: `title`, `content`, `dateCreated`, `status` (Enum: *Draft*, *Published*), `numberOfComments`.
- **Comment**: `content`, `articleId`, `type` (Enum: *Approved*, *Rejected*), `dateCreated`.
- **Author**: `firstName`, `lastName`, `email`, `role` (Enum: *Contributor*, *Editor*).

## 🎯 Project Objectives

- Develop a web-based application using **Java Servlets, JSP, JSTL**, and **Hibernate**.
- Implement CRUD operations for managing articles, comments, and authors.
- Adapt the existing database schema for web-based usage.
- Design an intuitive, responsive user interface.
- Apply agile project management principles throughout the development.
- Use advanced Java concepts and best practices to ensure performance and scalability.

## 🛠️ How to Use Blogify

### Prerequisites

Before running **Blogify**, ensure you have the following installed:

- **Java 8** or later
- **MySQL** database with the necessary tables and schema (setup instructions below)
- **JDBC Driver** for MySQL
- **Hibernate** for ORM
- **Apache Tomcat** for deployment

### Installation
1. Clone the repository to your local machine:
   ```bash
   git clone https://github.com/zinebMachrouh/Bloogy.git
   cd Bloogy


2. Create a MySQL database and run the SQL script to set up the necessary tables:
    ```bash
    psql -U yourusername -d yourdatabase -f resources/Bloogy.sql
    ```
   Replace `yourusername` and `yourdatabase` with your MySQL credentials and database name.

3. Update the `hibernate.cfg.xml` file in the `resources` directory with your database connection details.
4. Deploy the application on Apache Tomcat or run it locally via your browser at `http://localhost:8080/Bloogy`.


## 🎉 Get Started with Bloogy Today!

For any questions, feedback, or suggestions, feel free to reach out to us. 📧