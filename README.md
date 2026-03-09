# 🛒 CommerceCore API
![Java](https://img.shields.io/badge/Java-17-orange)
![Spring Boot](https://img.shields.io/badge/SpringBoot-3.x-brightgreen)
![Spring Security](https://img.shields.io/badge/SpringSecurity-JWT-blue)
![MySQL](https://img.shields.io/badge/MySQL-Database-blue)
![Maven](https://img.shields.io/badge/Maven-Build-red)
![REST API](https://img.shields.io/badge/API-RESTful-yellow)

### Secure E-Commerce Backend System (Spring Boot + JWT)

**CommerceCore API** is a secure, layered e-commerce backend system built using **Spring Boot** and **Spring Security**.

It demonstrates how a **real-world backend application works behind the scenes**, including authentication, authorization, product management, cart handling, and order processing.

The project follows **clean layered architecture**, **RESTful API design**, **DTO-based communication**, and **JWT-based security**, making it a strong example of **production-style backend development**.

---

# 🚀 Key Features

## 🔐 Authentication & Security
- JWT-based authentication  
- Secure login system  
- Role-based access control  
- Endpoint protection using Spring Security  
- Stateless session management  

## 👤 User Management
- User registration and login  
- Secure password handling  
- Role-based permissions (Admin / User)  

## 📦 Product Management
- Create, update, delete, and fetch products  
- Product categorization  
- Product image support  
- Inventory management  

## 🛒 Cart Management
- Add products to cart  
- Update cart items  
- Remove items from cart  
- Cart retrieval for users  

## 📦 Order Processing
- Place orders from cart  
- Inventory updates after order placement  

## 🧾 API Architecture
- RESTful API design  
- Request and Response DTOs  
- Clean layered architecture  

## ⚠️ Error Handling
- Global exception handling  
- Custom exceptions for meaningful errors  
- Consistent API error responses  

---

# 🛠 Tech Stack

| Technology | Purpose |
|-----------|--------|
| Java | Core programming language |
| Spring Boot | Backend application framework |
| Spring Security | Authentication and authorization |
| JWT (JSON Web Token) | Secure stateless authentication |
| Spring Data JPA | Database interaction |
| Hibernate | ORM framework |
| MySQL | Relational database |
| Maven | Dependency management |
| REST APIs | Communication layer |

---

# 🔐 JWT Authentication Flow

The authentication process follows a **secure JWT token-based flow**.

## Step-by-Step Flow

1. User sends login request with credentials.

2. Spring Security authenticates the user.

3. If authentication succeeds:
- A **JWT token is generated**.

4. The token is returned to the client.
{
  "token": "JWT_TOKEN"
}

# 🏗 System Architecture

The backend follows a **layered architecture pattern**.

Client (Postman / Frontend)
            │
            ▼
      Controller Layer
            │
            ▼
       Service Layer
     (Business Logic)
            │
            ▼
      Repository Layer
       (Spring Data JPA)
            │
            ▼
        MySQL Database

    ### Architecture Layers

**Controller Layer**
- Handles HTTP requests and responses.

**Service Layer**
- Contains core business logic.

**Repository Layer**
- Handles database operations using Spring Data JPA.

**Database Layer**
- MySQL relational database storing application data.

🗄 Database The system uses MySQL relational database.
 #Main Tables
-Users
-Roles
-Products
-Categories
-Carts
-CartItems
-Orders
-OrderItems

🧪 API Testing 
You can test the APIs using: 
-Postman -Insomnia

# 📚 What This Project Demonstrates

- Building secure REST APIs with Spring Boot
- Implementing JWT Authentication
- Role-based authorization with Spring Security
- Clean layered architecture
- DTO-based API communication
- Relational database modeling
- Global exception handling
