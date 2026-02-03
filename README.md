## Table of Contents
- [Introduction](#introduction)
- [Features](#features)
- [Database](#database)
- [Getting Started](#getting-started)
    - [Prerequisites](#prerequisites)
    - [Installation](#installation)
    - [Running the Application](#running-the-application)
    - [API Documentation](#api-documentation)
- [Configuration](#configuration)

## Introduction
This project is a simple library system. It consists of two microservices:

- **ms-book**: Handles book-related actions.
- **ms-borrower**: Handles borrower-related actions.

## Features
- Register a new book: ```User is able to register a new book to the system at one time.```
- Get book list: ```User is able to get a list of book in the library system.```
- Borrow book: ```User is able to borrow a book at one time. To borrow N book, trigger the api N times.```
- Return book: ```User is able to return a book at a time. To return N book, trigger the api N times.```
- Register a new borrower: ```User is able to register a new borrower to the system at one time.```
- Get borrower list: ```User is able to get a list of borrower in the library system.```

## Database
- MSSQL Database

## Getting Started

### Prerequisites
- Java 21
- Maven 3.9.11

### Installation
This repository contains multiple microservices.
Each microservice is an independent Maven project with its own pom.xml.

When opening the project for the first time in IDE, please follow these steps:

1. Open the project root folder in IDE.

For each microservice directory:

2. Right-click the pom.xml

3. Select “Add as Maven Project”

Once all Maven projects are loaded, run the following command for each microservice:

    mvn clean install

### Running the Application

1. Run database migration first

   Navigate to the `library-db-migration` module and start the service to trigger Flyway to create/update the database schema:
   ```bash
   cd library-db-migration
   mvn spring-boot:run

2. Run the microservices

    After the database is ready, navigate to each microservice and start it:
    ```bash
   cd ms-book
   mvn spring-boot:run
   
   cd ms-borrower
   mvn spring-boot:run

- **ms-book**: will be accessible at `http://localhost:8080`.  
- **ms-borrower**: will be accessible at `http://localhost:8081`.

### API Documentation

1. Using Postman Collection:

User is allowed to test the system api using [Postman Collection](https://github.com/L-ee98/LibrarySytstem/tree/main/postman/collections)

## Configuration

The application can be configured using the `application.properties` file located in the `src/main/resources` directory.
