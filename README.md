# E-Commerce App

## Overview
The **E-Commerce App** is a modular application built using **Java**, **Spring Boot**, and **Maven**. It follows the **Hexagonal Architecture** (also known as **Ports and Adapters Architecture**) to ensure a clean separation of concerns, making the application highly maintainable and testable.

---

## Features
- **Hexagonal Architecture**: Decouples the core business logic from external systems (e.g., databases, frameworks) using ports and adapters.
- **User Management**: Register, login, and manage users with role-based access control.
- **Role-Based Authorization**: Secure endpoints based on user roles (e.g., `ADMIN`, `USER`).
- **JWT Authentication**: Secure API endpoints with JSON Web Tokens.
- **Persistence Layer**: Uses JPA for database interactions.
- **Modular Design**: Separation of concerns with clear domain, infrastructure, and application layers.

---

## Technologies Used
- **Java 17**
- **Spring Boot**
- **Spring Security**
- **Maven**
- **JPA/Hibernate**
- **Lombok**
- **H2 Database** (or replace with your preferred database)
- **OpenAPI/Swagger** for API documentation

---

## Project Structure
The project follows a **Hexagonal Architecture** pattern with the following layers:

- **Domain Layer**: Contains core business logic and domain models. This layer is independent of any external frameworks or technologies.
- **Application Layer**: Handles use cases and application-specific logic. It defines input and output ports to interact with the domain layer.
- **Infrastructure Layer**: Manages persistence, security, and external integrations. It implements the adapters for the ports defined in the application layer.

### Key Directories
- `src/main/java/com/abdel/business/domain`: Domain models and value objects.
- `src/main/java/com/abdel/business/usecase`: Application use cases and ports.
- `src/main/java/com/abdel/infrastructure`: Security, persistence, and configuration.
- `src/main/java/com/abdel/infrastructure/web`: REST controllers for handling API requests.

---

## Getting Started

### Prerequisites
- **Java 17** or higher
- **Maven 3.8+**

### Installation
1. Clone the repository:
   ```bash
   git clone https://github.com/abdoulc/ecommerce-app.git
   cd ecommerce-app