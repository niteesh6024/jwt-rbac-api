# Simple API with JWT Authentication and RBAC
## Project Overview

This project is a RESTful API built using Java Spring and MongoDB. It includes JWT-based user authentication and Role-Based Access Control (RBAC). The API features user registration and login, secure password hashing, and CRUD operations for a sample resource called "projects".

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
- **Arena** is the main interface for the game, which includes methods for starting the tournament and managing matches.
- **MagicalArena**: Implements the `Arena` interface and manages the gameplay between two players.
- **ArenaFactory**: A factory class used to create the arena based on the chosen arena type.
- **RandomPlayerSelector**: Responsible for selecting two random players from the available list.

### 2. **Project**
- **Player**: Interface that defines basic player attributes and actions, such as health, attack, defense, and damage handling.
- **BasicPlayer**: Implements the `Player` interface and represents a basic player type with a specified health, attack, and defense.
- **PlayerFactory**: A factory class for creating different types of players.

### 3. **Role**
- **Dice**: Interface that defines the dice rolling behavior.
- **SixSidedDice**: Implements a 6-sided dice used by players for attacking and defending.
- **DiceFactory**: A factory class used to create the dice based on the chosen type.

## Project Structure
- src/main/java (Main.java): Contains the main source code files which is inside org.magical_arena package.
- src/test/java (PlayerTest.java, DiceTest.java and ArenaTest.java: Contains the test source code files which is inside org.magical_arena package.
- pom.xml: Maven project configuration file.
- src/main/resources/arena_config.txt The game uses a configuration file to specify the arena settings, dice type, player types, and their attributes.


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
    - Run the Main.java file (located in src/main/java) as a Java application to start the application.

5. Open postman: 
**Create an account**:
    - hit the endpoint http://localhost:8080/api/auth/signup with POST
    - body: 
   ```body
    {
    "username": "<usename>",
    "email": "<email>",
    "roles": ["user","admin"], // based on need
    "password": "xxxx"
    }
    ```
   - you will see wether the account was created or not. if you try to create a account with existing username or email. it won't allow
**login to get the jwt token**
   - hit the endpoint http://localhost:8080/api/auth/signin with POST
   - body:
      ```body
       {
       "username": "<usename>",
       "password": "xxxx"
       }
       ```
   - you will get jwt authentication token in the repsonse body. you can use  for further requests.
6. To view the projects:
   - hit the endpoint http://localhost:8080/api/project with get and barer token got from the sign in
   - any user with the valid token can view the projects

7. To Update the projects:
   - hit the endpoint http://localhost:8080/api/project with POST/PUT/DELETE and barer token got from the sign in
   - the body should you have was:
      ```body
       {
       "name": "<project name>", // you can update the project name
       "id": "id of the prject>", //you can not update the id
       "description": "<description" //you can chnage the description
       }
       ```
   - only admin users with the valid token can do CRUD operations on the projects

## Unit Tests
The project includes unit tests to ensure the correctness of the code. The tests use JUnit 5.9.3.
