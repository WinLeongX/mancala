# Mancala

This is a Spring Boot application of the board game Mancala using Thymeleaf

## Prerequisites

Before running the application, ensure you have the following installed:

- **Java**: JDK 17 or later
- **Maven**: 3.8 or later

## Getting Started

### Build the Application

Use Maven to build the application:

```bash
mvn clean install
```

### Run the Application

Run the application using the Maven `spring-boot:run` command or the compiled JAR file.

#### Option 1: Using Maven
```bash
mvn clean spring-boot:run
```

#### Option 2: Using the JAR File

First, build the JAR file:
```bash
mvn clean package
```
Then run the JAR file:
```bash
java -jar target/mancala-0.0.1.jar
```

### Access the Application

Once the application starts, you can access the Mancala game with a browser to this URL:

- **Base URL**: `http://localhost:8080`

## Configuration

The application can be configured using the `application.properties` file located in the `src/main/resources` directory:

```properties
spring.application.name=Mancala App
```

## Running Tests

To run the tests, execute:

```bash
mvn clean test
```