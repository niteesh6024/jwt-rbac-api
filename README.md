# Simple API with JWT Authentication and RBAC
## Project Overview

This project is a RESTful API built using Java Spring and MongoDB. It includes JWT-based user authentication and Role-Based Access Control (RBAC). The API features user registration and login, secure password hashing, and CRUD operations for a resource called "projects".

## Features

1. **User Registration**: Users can register with a username and password. Passwords are hashed using bcrypt.

2. **JWT Authentication**: Users can log in and receive a JWT token for authentication

3. **Role-Based Access Control (RBAC)**:
    - Admin Role: Has full access (create, read, update, delete) to resources
    - User Role: Has read-only access to resources.
4. **MongoDB Storage**: User and project data are stored in a MongoDB database using Docker for containerization

## Prerequisites
Ensure the following software is installed on your system:
- **Java Version**: The project is compatible with JDK 17 or higher.
- **Docker**: for running MongoDB container
- **JUnit 5.9**: For unit testing

## Classes Design

### 1. **User**
- Represents users of the application with properties like username, email, and hashed password

### 2. **Role**
- Represents user roles stored in a MongoDB collection.
- ERole Enum: Defines roles such as ROLE_USER and ROLE_ADMIN

### 3. **Project**
- Represents a project entity with properties like id, name, and description

## Project Structure
- src/main/java (Main.java): Contains main application code, including controllers, services, and entity classes.
- src/test/java : Contains unit tests for controllers and services.
- pom.xml: Maven project configuration file.
- src/main/resources/application.properties: Configuration file for the Spring Boot application.

## How to Run

1. Clone the Repository
    ```git
   git clone https://github.com/niteesh6024/jwt-rbac-api.git
   ```
2. run the mongo docker container
    ```docker
     docker run -d --name mongodb-container \
      -e MONGO_INITDB_ROOT_USERNAME=user \
      -e MONGO_INITDB_ROOT_PASSWORD=codingsphere \
      -e MONGO_INITDB_DATABASE=codingsphere-db \
      -p 27017:27017 mongo
   ```

3. Import the Project
    - Open Eclipse/intellj IDE.
    - Go to File -> Import -> Existing Maven Projects.
    - Browse to the project directory and select it.
    - Click Finish.

4. Run the Application:
    - Run the Main.java file (located in src/main/java) as a Java application to start the API

## Interact with the API Using Postman

1. **Create an account**:
    - Endpoint: POST http://localhost:8080/api/auth/signup
    - Request body: 
   ```body
    {
    "username": "<usename>",
    "email": "<email>",
    "roles": ["user","admin"], // based on need
    "password": "xxxx"
    }
    ```
   - Response: Success or error message. Duplicate usernames or emails will be rejected.

2. **Login to Get JWT Token**
   - Endpoint: POST http://localhost:8080/api/auth/signin
   - Request body:
      ```body
       {
       "username": "<usename>",
       "password": "xxxx"
       }
       ```
   - Response: JWT token that can be used for authenticated requests.

3. To view the projects:
   - Endpoint: GET http://localhost:8080/api/project
   - Authorization: Include the JWT token in the Authorization header as Bearer <your-jwt-token>
   - Roles: Any user with a valid token can view projects.

4. CRUD Operations on Projects::
   - Endpoint: POST/PUT/DELETE http://localhost:8080/api/project
   - Request body:
      ```body
       {
       "name": "<project name>", // you can update the project name
       "id": "id of the prject>", //you can not update the id
       "description": "<description" //you can change the description
       }
       ```
   - Authorization: Only admin users with a valid token can perform CRUD operations.

## Unit Tests
The project includes unit tests to ensure the correctness of the code. The tests use JUnit 5.9.3.


## swagger doc
- http://localhost:8080/swagger-ui/index.html