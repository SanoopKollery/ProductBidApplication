## Transaction CQRS Service

## Installation 🔧
To generate jar files run the following command in root path:

```bash
mvn clean package
```
Run containers of *Apache Kafka, Zoookeper and Mongo*.

```bash
docker-compose up 
```

Wait a minute, when you see all containers running yo can launch the Spring Boot microservices:

### Microservice for command
Run the following command:

```bash
java -jar demo-command/target/transaction-command-0.0.1-SNAPSHOT.jar
```

### Microservice for query
In other terminal run the following command:

```bash
java -jar demo-query/target/transaction-query-0.0.1-SNAPSHOT.jar
```

Start Eureka and API gateway services

# Running project ⚙️
Once all microservice started you can test this service using the command endpoint and service endpoint. 

Create Bid
 
 POST
  
   http://localhost:8082/tc/e-auction/api/v1/buyer/place-bid
   
   {
	"firstName" : "Kiran",
	"lastName":"Kiran",
	"address":"TestAddress",
	"city":"TestCity",
	"state":"TestState",
	"pin":"12345",
	"phone":"1234567890",
	"email":"kiran@gmail.com",
	"productId":"7d5927a7-230e-4d52-b75e-e02a9a16f0b2",
	"bidAmount":"1000.00"
   }
  
 Update Bid
 
 PUT
 
 localhost:8082/tc/e-auction/api/v1/buyer/update-bid/7d5927a7-230e-4d52-b75e-e02a9a16f0b2/kiran@gmail.com/2000.00
 
 Get Bids
 
 GET
 
 http://localhost:8082/tq/e-auction/api/v1/seller/show-bids/7d5927a7-230e-4d52-b75e-e02a9a16f0b2
 
 



