# 🎓 EduTasker Backend — Spring Boot REST API

EduTasker is a collaborative academic **Project & Task Management System** designed for **students and instructors**.  
This repository contains the **backend REST API built with Spring Boot**, responsible for authentication, project/task management, submissions, and reviews.

---

## 🚀 Features

- 🔐 JWT Authentication & Spring Security
- 👥 Role-based access (Student & Instructor)
- 📘 Instructors create & assign projects
- 📌 Task management inside each project
- 📂 Students submit final project links,Studeents can do CRUD on Task (Dividing project into tasks )
- 📝 Instructor reviews with feedback,Track task progress
- 🗄 PostgreSQL + Hibernate + JPA

---

## 🛠 Tech Stack

| Layer | Technology |
|-------|------------|
| Language | Java 17+ |
| Framework | Spring Boot |
| Security | Spring Security + JWT |
| Database | PostgreSQL |
| ORM | Hibernate / JPA |
| Build Tool | Maven |
| Helpers | DevTools |

---

## 📌 Prerequisites

- Java 17+
- PostgreSQL
- Maven
- IDE (IntelliJ / Eclipse / VS Code)

---

## ⚙️ Configuration

Update `src/main/resources/application.properties`:

```properties
spring.datasource.url=jdbc:postgresql://localhost:5432/edutasker
spring.datasource.username=postgres
spring.datasource.password=yourpassword

spring.jpa.hibernate.ddl-auto=update
spring.jpa.show-sql=true
spring.jpa.properties.hibernate.dialect=org.hibernate.dialect.PostgreSQLDialect


##project Structure
src/main/java/com/edutasker
│── configuration
|── controller
│── Dto
│── Entity
│── Repositoty
│── security (JWT)
|── Service
└── EduTaskerApplication.java

## 🚀 Getting Started

### 📥 Clone
```bash
git clone <repo-url>
cd to this folder

▶️ Running the Application
Using Maven
mvn spring-boot:run

Using JAR
mvn clean package
java -jar target/edutasker-backend.jar


Backend runs on:

http://localhost:8080


## 🌐 Frontend Repository

EduTasker includes a separate Vite-based frontend (HTML + CSS (Tailwind)+ Js) that consumes this backend API.

🔗 Frontend GitHub Repo: **<https://github.com/Vinayak-v12/EduTasker-Frontend>**

To run both services:
1. Start the backend → `http://localhost:8080`
2. Start the frontend → `http://localhost:5173`

