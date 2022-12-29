# Movies-database-api

This is a simple API for managing a movie database. It provieds endpoints for CRUD operations that are creating, reading, updating and deleting a movie.

## Getting Started

These instructions will help you get a copy of the project and running on your local machine for developing and testing purposes.

### Prerequisites

- Java 8 or above
- Gradle 6.4 or later
- MySQL 5.7 or later

### Installing

1. Clone the Repository using git clone
2. Navigate to the project directory
3. Build the project
4. Run the application

The API will be available at http://localhost:8080.

## API endpoints

- 'GET /movies': Get a list of all movies
- 'GET /movies/{id}': Get a movie by ID
- 'POST /movies/{id}': Create a new movie
- 'PUT /movies/{id}': Update an existing movie specified with that ID
- 'DELETE /movies/{id}': Delete a movie

## Built With

- [Spring Boot](https://spring.io/projects/spring-boot) - The web framework used
- [Gradle](https://gradle.org/) - Dependency Management
- [MySQL](https://www.mysql.com/) - Database
