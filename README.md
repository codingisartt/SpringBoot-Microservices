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

Client
   |
Catalog Service
   |        |
Movie Info  Rating Data
   |
Discovery Server (Eureka)

## Technologies Used

Java 17
Spring Boot 2.7.x
Spring Cloud 2021.0.x
Netflix Eureka
Maven

## How to Run the Application
Start Discovery Server
  cd discovery-server
  mvn spring-boot:run
Eureka Dashboard: http://localhost:8761

Start Movie Info Service
  cd movie-info-service
  mvn spring-boot:run
  
Start Rating Data Service
  cd rating-data-service
  mvn spring-boot:run

Start Catalog Service
  cd catalog-service
  mvn spring-boot:run

## Service Registration Check

After all services are running, the following services should appear on the Eureka Dashboard: http://localhost:8761

MOVIE-INFO-SERVICE
RATING-DATA-SERVICE
CATALOG-SERVICE

## Sample API Endpoint
GET http://localhost:8081/catalog/{userId} 
GET http://localhost:8081/catalog/john

## Key Concepts Covered

Microservices architecture
Service Discovery with Eureka
Spring Cloud Netflix integration
RESTful inter-service communication
Centralized service registration

<img width="1221" height="518" alt="image" src="https://github.com/user-attachments/assets/c3e26e51-cad2-465f-afed-aaf1d4ca5f59" />


