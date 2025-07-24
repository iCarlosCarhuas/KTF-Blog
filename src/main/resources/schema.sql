drop database if exists ktf_blog;

create database ktf_blog;

use ktf_blog;



-- Security Schema

CREATE TABLE profile
(
	profile_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	profile_name VARCHAR(50) NULL

);

CREATE TABLE role
(
	role_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	role_name VARCHAR(200) NOT NULL,
	role_route VARCHAR(200) NOT NULL,
	role_description VARCHAR(200) NOT NULL
)

CREATE TABLE access
(
	access_id BIGINT AUTO_INCREMENT PRIMARY KEY,
	profile_id BIGINT NOT NULL,
	role_id BIGINT NOT NULL,

	CONSTRAINT fk_profileId FOREIGN KEY (profile_id)
	REFERENCES profile (profile_id),
	CONSTRAINT fk_roleId FOREIGN KEY(role_id)
	REFERENCES role (role_id)
)

-- Blog Schema
CREATE TABLE users (
    user_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    user_publicID VARCHAR(64) unique,
    user_image VARCHAR(255) null,
    username VARCHAR(50) NOT NULL UNIQUE,
    name VARCHAR(255) NOT NULL,
    last_name VARCHAR(255) NOT NULL,
    email VARCHAR(255) NOT NULL,
    password VARCHAR(255) NOT NULL,
    profile_id BIGINT NOT NULL,
    role_id BIGINT NOT NULL,

    CONSTRAINT fk_profileId2 FOREIGN KEY (profile_id)
    REFERENCES profile (profile_id),
    CONSTRAINT fk_roleId2 FOREIGN KEY (role_id)
    REFERENCES role (role_id)
);

CREATE TABLE posts (
    post_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    content TEXT NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
    CONSTRAINT fk_categoryId FOREIGN KEY (category_id)
	REFERENCES categories(category_id);
);

CREATE TABLE categories (
    category_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    name VARCHAR(100) NOT NULL UNIQUE,
    description TEXT
);



CREATE TABLE quizzes (
    quiz_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    title VARCHAR(255) NOT NULL,
    post_id BIGINT NOT NULL,
    FOREIGN KEY (post_id) REFERENCES posts(post_id)
);


CREATE TABLE questions (
    question_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    text VARCHAR(255) NOT NULL,
    quiz_id BIGINT NOT NULL,
    FOREIGN KEY (quiz_id) REFERENCES quizzes(quiz_id)
);


CREATE TABLE answers (
    answer_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    answer_text VARCHAR(255) NOT NULL,
    question_id BIGINT NOT NULL,
    user_id BIGINT NOT NULL,
    FOREIGN KEY (question_id) REFERENCES questions(question_id),
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);


CREATE TABLE rankings (
    ranking_id BIGINT AUTO_INCREMENT PRIMARY KEY,
    points INT NOT NULL,
    user_id BIGINT UNIQUE,
    FOREIGN KEY (user_id) REFERENCES users(user_id)
);
