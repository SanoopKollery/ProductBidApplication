
# PRODUCT BIDDING CQRS APPLICATION


## Requirements 📋
To install and run this project you may be installes next requirements:
- jdk8
- maven
- docker and docker-compose

## Installation 🔧
To generate jar files run the following command in root path:

```bash
mvn clean package
```
Run containers of *Apache Kafka, Zoookeper and Mongo*.

```bash
docker-compose up -d
```

Wait a minute, when you see all containers running yo can launch the Spring Boot microservices:

### Microservice for command
Run the following command:

```bash
java -jar demo-command/target/product-command-0.0.1-SNAPSHOT.jar
```

### Microservice for query
In other terminal run the following command:

```bash
java -jar demo-query/target/product-query-0.0.1-SNAPSHOT.jar
```

Start Eureka and API gateway services

# Running project ⚙️
Once all microservice started you can test this service using the command endpoint and service endpoint. 

Create Prouct

POST
http://localhost:8082/pc/e-auction/api/v1/seller/add-product

{  
    "productName": "Bangle",
    "shortDescription": "Bangle",
    "description": "Bangle",
    "category": "ORNAMENT",
    "startingPrice": 500.00,
    "bidEndDate": "2022-11-25 00:00:00"
}

GET Products

GET
http://localhost:8082/pq/e-auction/api/v1/seller/get-product

DELETE PRODUCT

DELETE
http://localhost:8082/pc/e-auction/api/v1/seller/delete/7d5927a7-230e-4d52-b75e-e02a9a16f0b2