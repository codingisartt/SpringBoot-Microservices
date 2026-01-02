# Movie Microservices Project
A Spring Boot microservices project built using Netflix Eureka for service discovery. The system demonstrates service-to-service communication and microservice architecture principles.

## Services

This project consists of the following microservices:

| Service Name            | Description                       | Port   |
| ----------------------- | --------------------------------- | ------ |
| **Discovery Server**    | Eureka Service Registry           | `8761` |
| **Movie Info Service**  | Provides movie details            | `8082` |
| **Rating Data Service** | Provides movie ratings            | `8083` |
| **Catalog Service**     | Aggregates movie info and ratings | `8081` |

## Architecture Overview

Client communicates with Catalog Service
Catalog Service fetches data from:
   Movie Info Service
   Rating Data Service
All services are registered with Eureka Discovery Server

## Technologies Used

- Java 17
- Spring Boot 2.7.x
- Spring Cloud Netflix Eureka
- Maven
- RESTful APIs

## How to Run the Application
Start Discovery Server
```bash
  cd discovery-server
  mvn spring-boot:run
```

Start Movie Info Service
```bash
  cd movie-info-service
  mvn spring-boot:run
```
Start Rating Data Service
```bash
  cd rating-data-service
  mvn spring-boot:run
```
Start Catalog Service
```bash
  cd catalog-service
  mvn spring-boot:run
```
## Service Registration Check

After all services are running, the following services should appear on the Eureka Dashboard: http://localhost:8761

MOVIE-INFO-SERVICE
RATING-DATA-SERVICE
CATALOG-SERVICE

## Sample API Endpoint
- GET http://localhost:8081/catalog/{userId} 
- GET http://localhost:8081/catalog/john

## Key Concepts Covered

- Microservices architecture
- Service Discovery with Eureka
- Spring Cloud Netflix integration
- RESTful inter-service communication
- Centralized service registration

<img width="1221" height="518" alt="image" src="https://github.com/user-attachments/assets/c3e26e51-cad2-465f-afed-aaf1d4ca5f59" />

## External API Integration

This service fetches movie details from the **TMDB (The Movie Database) API**.

- Calls TMDB using `RestTemplate`
- Maps the response to `MovieSummary`
- Returns selected fields as a `Movie` object

**External API:**  
`https://api.themoviedb.org/3/movie/{movieId}`

> API key is stored in `application.properties` and not committed to the repository.

## Example API Call

http://localhost:8082/movies/550

<img width="605" height="131" alt="image" src="https://github.com/user-attachments/assets/415d34be-f72e-49af-b6f9-33ead05317ab" />

