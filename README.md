Welcome to the Test Project for BrightTALK
==================================================

## General info
This is the service that allows you to create(store) and retrieve a user realm.

A User Realm is a context for the registration and authentication of a user, which comprises
a unique integer identifier, a unique name, an (encryption) key and a description.
	
## Technologies
Project is created with:
* Java 8
* Maven
* Spring Boot
* MySQL

## Setup
Before start need to update the following properties:

**spring.datasource.url** - the url for database connection  
**spring.datasource.username** - database username  
**spring.datasource.password** - database password

The realm table will be created automatically after the application starts.

## Run the app

Run the next command: `mvn clean install spring-boot:run`  

Now you can open `http://localhost:8080/swagger-ui.html#/realm-controller` to available APIs.  
Also you can import the "postman_collection.json" to Postman.
