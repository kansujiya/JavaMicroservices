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
* H2 Database
* Actuator
* netflix-eureka-client
* resilience4j
* spring-cloud-starter-config
* spring-cloud-starter-netflix-eureka-server
* spring-cloud-starter-gateway
* JPA

## Webservices
http://localhost:8000/currency-exchange/from/AUD/to/INR
http://localhost:8100/currency-conversion-feign/from/AUD/to/INR/quantity/200
http://localhost:8000/h2-console/
http://localhost:8761/

## After Service Discovery
http://localhost:8765/CURRENCY-EXCHANGE/currency-exchange/from/AUD/to/INR \n
http://localhost:8765/CURRENCY-CONVERSION/currency-conversion-feign/from/AUD/to/INR/quantity/200 \n
http://localhost:8765/currency-conversion/currency-conversion/from/AUD/to/INR/quantity/200

## After API Gateway & Client side load balancer
http://localhost:8765/get \n
http://localhost:8765/currency-exchange/from/AUD/to/INR \n
http://localhost:8765/currency-conversion-feign/from/AUD/to/INR/quantity/200 \n
http://localhost:8765/currency-conversion-new/from/AUD/to/INR/quantity/200 \n
http://localhost:8000/retry-api \n
http://localhost:8000/circuit-breaker-api \n
