# Java MicroServices | Web Service | API Gateway | Naming Server | Routing | Logging | Resilience | H2 Databse Configrations | Tracing | Zepkin | Docker | Docker-compose | Cernterlised configrations
Source code have four componet. Currency-Exchange & Currency-Conversion are mirco services & Communicating with each other using feign & RestTemplet. Naming Server is Service discovery/Service registry to register microservices instances. Last but not least Client side API gateway to manage all incoming/outgoing requests using routings.

* ##### Note1 - Currency-Exchange is built with H2 Memory database and data will be seeded using [data.sql](https://github.com/kansujiya/JavaMicroservices/blob/master/currency-exchange-service/src/main/resources/data.sql) on app start.


* ##### Note2 - Centerlised configration server & docker images of each componet will added sooner. Stay tuned. :)

## Table of contents
* [Technologies](#technologies)
* [Componets](#componets)
* [Setup](#setup)
* [APIs Documentations](#apis)
* [Docker](#docker) 

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
* H2 console link - http://127.0.0.1:8000/h2-console/
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

## Docker
### To run all application componets run below command in terminal to start all microservices in local.
* docker-compose up
### Docker image commands
* Docker image pull <name><tag:optional>[To pull new docker image in local from docker registry/hub]
* Docker image search <name>[To search docker image from docker registry]
* Docker image history <image_id>[To see all added layer in a docker image]
* Docker image inspect <image_id>[To see all available configuration of image like volume/entry-point/port etc]
### Docker container commands
* - Docker run -p <host-port>:<source:port> -d(For detached mode) <image-name><tag:optional> [To start a docker container in Detached mode/background mode not with lifecycle of terminal]
* Docker log -f <container-id>[To see the logs of a running container in tail/continues mode with -f]
* Docker container inspect <container_id> [To see all configuration of a running container like network/entry-point/directory etc.]
* Docker container ls -a [To check all running and stopped container in docker daemon process with -a option]
* Docker container prune [remove stopped container]
* Docker container stop/pause/unpause/kill [To stop/pause a running containers]
* Docker container -p 5000:5000 -d —restart=always [docker desktop client restart will also restart container]
### Docker stat & system
* Docker event [To see docker containers stats in docker daemon/engin]
* Docker container top [To check what process/task running in a specific container]
* Docker stats [To see running container memory/network & other metrics]
* Docker run -p 5000:5000 -d -m 512m —cpu-quota 5000 -d <image-name>[To allow uses of RAM memory of Host machine and CPU uses permission to a specific docker]
* Docker system df [All images/containers in docker client daemon/engine]
