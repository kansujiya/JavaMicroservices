# Java MicroServices | Web Service | API Gateway | Naming Server | Routing | Logging | Resilience | H2 Database Configurations | Tracing | Zepkin | Docker | Docker-compose | Centralised configurations | Container orchestration | Kubernetes
Source code have four component. Currency-Exchange & Currency-Conversion are micro services & Communicating with each other using feign & RestTemplet. Naming Server is Service discovery/Service registry to register micro-services instances. Last but not least Client side API gateway to manage all incoming/outgoing requests using routings.

* ##### Note1 - Currency-Exchange is built with H2 Memory database and data will be seeded using [data.sql](https://github.com/kansujiya/JavaMicroservices/blob/master/currency-exchange-service/src/main/resources/data.sql) on app start.

## Table of contents
* [Technologies](#technologies)
* [Components](#components)
* [Setup](#setup)
* [APIs Documentations](#apis)
* [Docker](#docker)
* [Kubernetes](#Kubernetes) 

## Technologies

Project is created with:
* Java: 8
* Springboot: 2.4.4
* Tomcat
* Docker
* Kubernetes
* H2 Database
* JPA (Java Persistence API)
* Actuator (For Health Check)
* spring-cloud-starter-openfeign (To communicate micro-service)
* netflix-eureka-client (To register micro-service in naming server)
* resilience4j (To manage failover, bulkhead, retry, circuit-breaker & many more)
* spring-cloud-starter-config (To manage Centralised Configuration to read profiles & environments variables using centralised GitHub repo)
* spring-cloud-starter-netflix-eureka-server (Naming server/Service registry/Service Discovery for micro-services)
* spring-cloud-starter-gateway (To manage Ingres & egress traffic routing from internet & in-between micro-services & client side load balancing for micro-service. Manage Authorisation, Authentication, logging, caching for micro-services.)

## Components
* currency-exchange-service
* currency-conversion-service
* naming-server
* api-gateway

## Setup
* Imports all components as maven projects.
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
### H2 in-memory database link
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
### To run all application components run below command in terminal to start all micro-services in local.
* docker-compose up
### Docker image commands
* Docker image pull {name}{tag:optional}[To pull new docker image in local from docker registry/hub]
* Docker image search {name}[To search docker image from docker registry]
* Docker image history {image_id}[To see all added layer in a docker image]
* Docker image inspect {image_id}[To see all available configuration of image like volume/entry-point/port etc]
### Docker container commands
* Docker run -p {host-port}:{source:port} -d(For detached mode) {image-name}{tag:optional} [To start a docker container in Detached mode/background mode not with lifecycle of terminal]
* Docker log -f {container-id}[To see the logs of a running container in tail/continues mode with -f]
* Docker container inspect {container_id} [To see all configuration of a running container like network/entry-point/directory etc.]
* Docker container ls -a [To check all running and stopped container in docker daemon process with -a option]
* Docker container prune [remove stopped container]
* Docker container stop/pause/unpause/kill [To stop/pause a running containers]
* Docker container -p 5000:5000 -d —restart=always [docker desktop client restart will also restart container]
### Docker stat & system
* Docker event [To see docker containers stats in docker daemon/engine]
* Docker container top [To check what process/task running in a specific container]
* Docker stats [To see running container memory/network & other metrics]
* Docker run -p 5000:5000 -d -m 512m —cpu-quota 5000 -d {image-name}[To allow uses of RAM memory of Host machine and CPU uses permission to a specific docker]
* Docker system df [All images/containers in docker client daemon/engine]

## Kubernetes
Kubernetes helps in container orchestration. Deployments with Kubernetes help a lot in micoservices architecture. Solve problems like service registry/discovery, logging, api gateway, tracing, autoscaling, centralised configuration etc. 
To make current project into Kubernetes enabled need to required few changes like need to remove eruka client, cloud configuration, cloud sleuth, & rabbitMQ dependences from maven & needs to update proxy class of conversion service.

### Kubernetes common commands
- Kubectl —version [kube controller | Client & server versions]
- Kubectl get events [events are like pod, pulling image, start container, replica, deployment & services]
- Kubectl get rs [running deployments]
- Kubectl get rs -o wide [details of running pods with images]
- Kubectl get componetsstatus[ To get state of master node components like oapiserver(kube-apiserver), distributed database(etcd),  Scheduler(kube-scheduler), controller manager(kube-controller-manager)]
- Kubectl get all [To get all pod, replicaset, services, load balancer etc]
- Kubectl delete all -l app={deployment-name} [To delete all services and pods of a specific deployment]
- Kubectl logs -f {pod_name} [To see all logs of a running container]
- Kubectl top pod [To see cpu utilisation by pods]
- Kubectl top node [To see cpu utilisation in node]
- Kubectl get hpa [To see horizontal pod autoscaler details of pod like min & max pod along with cpu utilisation details]
- Kubectl delete hpa {deployment-name} [To delete autoscaler of deployment]

### Kubernetes deployments commands
- Kubectl history deployment {deployment-name} [To see rollout revisions of any specific deployment]
- Kubectl create deployment {name} —image={docker registry path of image} [To create controller of deployment set a name along with image path]
- Kubectl expose deployment {name} —type=LoadBalancer —port=8080 [to deploy create deployment controller to outside world along with type of load balancer & exposed port]
- Kubectl autoscale deployment {deployment-name} —min=1 —max=3 —cpu-percent=75 [To manage autoscaling of container based on cpu utilisation with horizontal manner]
- Kubectl set deployment {deployment-name} {container-name}={image-name/path} [To deploy another version of image in running container]
- Kubectl get deployment [To get created deployment controllers]
- Kubectl scale deployment {name} —replicas=3 [To scale up running pod to manage load]
- Kubectl get deployment {deployment_name} -o yaml }} yaml {name.yaml} [To exported created deployment yaml file into local]
- Kubectl get service {deployment_name} -o yaml }} yaml {name.yaml} [To exported created services yaml file into local]
- Kubectl diff -f {deployment-name.yaml} [to see difference in deployment service]
- Kubectl apply -f {deployment-name.yaml} [to deploy using generated yaml]
- Kubectl undo deployment {deployment-name} —to—revision=1 [To rollout wrong deployment]

### Kubernetes pods commands
- Kubectl get pods [To get created pods | Pod is smallest deployable unit in Kubernetes, pod reside under a node]
- Kubectl get pods -o wide [To get running containers inside a pod & container can communicate on local host with each other]
- Kubectl explain pods [To get configuration or metadata of pod]
- Kubectl describe pods name [to see details of a pod about his node and namespace/profiling & label, status, IP, metadata etc]
- Kubectl delete pods {pod-id} [To delete a running pod and see updated in replica set]

### Kubernetes replica commands
- Kubectl get replicaset/rs [To get created replica | replica set ensure number of desire pod running inside a node, even if we explicitly delete any running pod, replica set monitoring pods, hence it will create new pod]
- Kubectl explain replicaset [To ensure specified running pods]


### Kubernetes services commands
- Kubectl get services [To get services like load balancer service/ cluster IP service, nodePort etc, Kubernetes services are responsible to provide external interface of services like api gateway]
- Kubectl get service/svc —watch [To see service update continue until all updates]

### Kubernetes configmap commands
- Kubectl create configmap {name} —form-literal={environment-variable-key}={environment-variable-value} [For centralised configuration create config]
- Kubectl get configmap [to get available configuration]
- Kubectl get configmap {name} -o yaml [To see detail of specific configuration in yaml form]
- Kubectl get configmap {name} -o yaml configmap.yaml [to export generated yaml file into local]
