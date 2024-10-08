-- Create database
CREATE DATABASE Bloogy;
USE Bloogy;

-- 1. Users Table with Roles for Admins, Users, Authors, and Contributors
CREATE TABLE users (
                       id INT AUTO_INCREMENT PRIMARY KEY,
                       username VARCHAR(255) UNIQUE NOT NULL,
                       email VARCHAR(255) UNIQUE NOT NULL,
                       password VARCHAR(255) NOT NULL,
                       is_active BOOLEAN DEFAULT TRUE NOT NULL,
                       date_joined TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL,
                       role ENUM('admin', 'user', 'author', 'contributor') NOT NULL
);

-- 4. Categories
CREATE TABLE categories (
                            id INT AUTO_INCREMENT PRIMARY KEY,
                            name VARCHAR(255) UNIQUE NOT NULL,
                            description VARCHAR(255) NOT NULL,
                            created_at TIMESTAMP DEFAULT CURRENT_TIMESTAMP NOT NULL
);

-- 2. Articles
CREATE TABLE articles (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          title VARCHAR(255) UNIQUE NOT NULL,
                          content VARCHAR(255) NOT NULL,
                          created_at TIMESTAMP NOT NULL,
                          published_at TIMESTAMP NULL,
                          status ENUM('draft', 'published') DEFAULT 'draft' NOT NULL,
                          author_id INT,
                          category_id INT,
                          FOREIGN KEY (author_id) REFERENCES users(id), -- References users table for article authors
                          FOREIGN KEY (category_id) REFERENCES categories(id)
);

-- 3. Comments
CREATE TABLE comments (
                          id INT AUTO_INCREMENT PRIMARY KEY,
                          content VARCHAR(255) NOT NULL,
                          date_creation TIMESTAMP NOT NULL,
                          status ENUM('approved', 'rejected') DEFAULT 'approved' NOT NULL,
                          article_id INT,
                          user_id INT,
                          FOREIGN KEY (article_id) REFERENCES articles(id),
                          FOREIGN KEY (user_id) REFERENCES users(id) -- References users table for comment authors
);



-- 5. Tags
CREATE TABLE tags (
                      id INT AUTO_INCREMENT PRIMARY KEY,
                      name VARCHAR(255) UNIQUE NOT NULL
);

-- 6. Article_Tags (Many-to-Many Relationship between Articles and Tags)
CREATE TABLE article_tags (
                              id INT AUTO_INCREMENT PRIMARY KEY,
                              article_id INT NOT NULL,
                              tag_id INT NOT NULL,
                              FOREIGN KEY (article_id) REFERENCES articles(id),
                              FOREIGN KEY (tag_id) REFERENCES tags(id)
);
