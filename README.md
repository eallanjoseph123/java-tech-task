# Lunch Microservice

The service provides an endpoint that will determine, from a set of recipes, what I can have for lunch at a given date, based on my fridge ingredient's expiry date, so that I can quickly decide what I’ll be having to eat, and the ingredients required to prepare the meal.

## Prerequisites

* [Java 11 Runtime](https://www.oracle.com/java/technologies/javase-jdk11-downloads.html)
* [Docker](https://docs.docker.com/get-docker/) & [Docker-Compose](https://docs.docker.com/compose/install/)

*Note: Docker is used for the local MySQL database instance, feel free to use your own instance or any other SQL database and insert data from lunch-data.sql script* 


### Run

1. Start database:

    ```
    docker-compose up -d
    ```
   
2. Add test data from  `sql/lunch-data.sql` to the database. Here's a helper script if you prefer:


    ```
    CONTAINER_ID=$(docker inspect --format="{{.Id}}" lunch-db)
    ```
    
    ```
    docker cp sql/lunch-data.sql $CONTAINER_ID:/lunch-data.sql
    ```
    
    ```
    docker exec $CONTAINER_ID /bin/sh -c 'mysql -u root -prezdytechtask lunch </lunch-data.sql'
    ```
    
3. Run Springboot LunchApplication


#Tech

1. Springboot
2. Mysql
3. Swagger
4. RestAPI

Accomplishments:

1. I have refactored most of the codes and restructured the packages.
![alt text](https://github.com/eallanjoseph123/java-tech-task/blob/master/docs/rezdy-package-structure.png?raw=true)
2. Added SWAGGER for the API documentation.
![alt text](https://github.com/eallanjoseph123/java-tech-task/blob/master/docs/swagger.png?raw=true)
3. Created Unit test for both Controller and Service using TDD.
4. put some comments in codes.
5. I used Spring data JPA to replace the LunchService::loadRecipes
6. Used lombok to remove boiler plate codes.

Run the application

0. mvn clean install -> run unit tests.
   mvn clean install -DskipTests=True -> skip unit tests
1. sudo docker-compse up
2. mvn spring-boot:run 
3. go to your browser and browse http://localhost:8080/swagger-ui.html#/





Issues:

1. The last criteria regarding the sorting not finish due to stackoverflow issue of JPA/Hibernate config.
