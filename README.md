# Java MicroServices | Web Service | API Gateway | Naming Server | Routing | Logging | Resilience | H2 Databse Configrations
Source code have four componet. Currency-Exchange & Currency-Conversion are mirco services & Communicating with each other. Naming Server is Service discovery to register microservices instances along with load balancing. Spring Eureka server is used for this purpose. Last but not least Client side API gateway to manage all incoming requests using routings.

* ##### Note - Currency-Exchange is built with H2 Memory database and data will be seeded using [data.sql](https://github.com/kansujiya/JavaMicroservices/blob/master/currency-exchange-service/src/main/resources/data.sql) on app start.

## Table of contents
* [Technologies](#technologies)
* [Componets](#componets)
* [Setup](#setup)
* [APIs Documentations](#apis)

## Technologies

Project is created with:
* Java: 8
* Springboot: 2.4.4
* Tomcate
* H2 Database
* JPA (Java Persistence API)
* Actuator (For Health Check)
* spring-cloud-starter-openfeign (To communicate microservice)
* netflix-eureka-client (To register microservice in naming server)
* resilience4j (To manage failover, bulkhead, retry, circuit-breaker & many more)
* spring-cloud-starter-config (To manage Centralized Configuration to read profiles & envoirments variables using centerlised github repo)
* spring-cloud-starter-netflix-eureka-server (Naming server/Service registry/Service Discovery for microservices)
* spring-cloud-starter-gateway (To manage ingres & egres traffic routing from internet & inbetween microservices & client side load balancing for microservice. Manage Authorization, Authetication, loging, chaching for microservices.)

## Componets
* currency-exchange-service
* currency-conversion-service
* naming-server
* api-gateway

## Setup
* Imports all componets as maven projects.
* currency-exchange-service will run on port 8000
* currency-conversion-service will run on port 8100 
* naming-server 8761 
* api-gateway 8765
* H2 console link - http://127.0.0.1:80/h2-console/
* Actuator Link(Health Check) - http://127.0.0.1:8000/actuator
* Swagger - http://127.0.0.1:8000/api-docs


## APIs Documentations
### currency-exchange-service api 
* http://localhost:8000/currency-exchange/from/AUD/to/INR
* http://localhost:8000/retry-api
* http://localhost:8000/circuit-breaker-api
### H2 in-momery databse link
* http://localhost:8000/h2-console/
### currency-conversion-service api
* http://localhost:8100/currency-conversion/from/AUD/to/INR/quantity/200
* http://localhost:8100/currency-conversion-feign/from/AUD/to/INR/quantity/200
### naming-server link
* http://localhost:8761/ 
### api-gateway link
* http://localhost:8765/get
* http://localhost:8765/CURRENCY-EXCHANGE/currency-exchange/from/AUD/to/INR
* http://localhost:8765/currency-exchange/from/AUD/to/INR
* http://localhost:8765/CURRENCY-CONVERSION/currency-conversion-feign/from/AUD/to/INR/quantity/200
* http://localhost:8765/currency-conversion/currency-conversion/from/AUD/to/INR/quantity/200
* http://localhost:8765/currency-conversion-feign/from/AUD/to/INR/quantity/200
* http://localhost:8765/currency-conversion-feign/from/AUD/to/INR/quantity/200
* http://localhost:8765/currency-conversion-new/from/AUD/to/INR/quantity/200
