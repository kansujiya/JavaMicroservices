# JavaMicroservices
Java MicroServices | Web Service | API Gateway | Naming Server | Routing | Logging | Resilience | H2 Databse Configrations

# Webservices
http://localhost:8000/currency-exchange/from/AUD/to/INR
http://localhost:8100/currency-conversion-feign/from/AUD/to/INR/quantity/200
http://localhost:8000/h2-console/
http://localhost:8761/

# After Service Discovery
http://localhost:8765/CURRENCY-EXCHANGE/currency-exchange/from/AUD/to/INR
http://localhost:8765/CURRENCY-CONVERSION/currency-conversion-feign/from/AUD/to/INR/quantity/200
http://localhost:8765/currency-conversion/currency-conversion/from/AUD/to/INR/quantity/200

# After API Gateway & Client side load balancer
http://localhost:8765/get
http://localhost:8765/currency-exchange/from/AUD/to/INR
http://localhost:8765/currency-conversion-feign/from/AUD/to/INR/quantity/200
http://localhost:8765/currency-conversion-new/from/AUD/to/INR/quantity/200
http://localhost:8000/retry-api
http://localhost:8000/circuit-breaker-api
