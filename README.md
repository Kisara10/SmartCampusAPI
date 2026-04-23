# Smart Campus REST API

A RESTful API built using Java and JAX-RS to manage rooms, sensors, and sensor readings in a smart campus environment.

## Features
- Room management (CRUD operations)
- Sensor management with room validation
- Sensor filtering by type
- Sensor readings with historical tracking
- Business rules (cannot delete room with sensors)
- Advanced error handling (409, 422, 403, 500)
- Logging of requests and responses

## API Endpoints

### Rooms
GET /api/v1/rooms  
POST /api/v1/rooms  
GET /api/v1/rooms/{id}  
DELETE /api/v1/rooms/{id}  

### Sensors
GET /api/v1/sensors  
POST /api/v1/sensors  
GET /api/v1/sensors/{id}  
DELETE /api/v1/sensors/{id}  
GET /api/v1/sensors?type=Temperature  

### Readings
GET /api/v1/sensors/{id}/readings  
POST /api/v1/sensors/{id}/readings  

## Technologies
- Java
- JAX-RS (Jersey)
- Apache Tomcat
- Maven

 ## How to Run
1. Clone the repository
   ```bash
   git clone https://github.com/Kisara10/SmartCampusAPI.git
3. Build the porject using Maven
   mvn clean package
4. Deploy the generated WAR file to Apache Tomcat
   Copy the WAR file from the target/ folder to:
    apache-tomcat/webapps/
5. Start the Tomcat server
6. Access the API at
   http://localhost:8080/SmartCampusAPI/api/v1


## Conceptual Report

### 1.	Lifecycle Question 
The default is to create an instance of JAX-RS resource classes on a request-by-request basis. This implies that a new object of the resource type is during every incoming HTTP request. This design does not share state among requests, and this minimizes the chances of concurrency problems. But as the application is using in memory data structures like maps and lists these must be declares as static so that they can store data between requests. Multi-threaded environments need to be properly synchronized to avoid race condition. 

### 2.	HATEOAS Question
One of the principles of the RESTful design is Hypermedia as the Engine of Application State (HATEOS), in which responses contain links to other resources. This enables clients to dynamically explore the API without hard coded URLs. HATEOAS is more flexible than the case of the static documentation, less complex at the client side and the API is more self-descriptive and responsive to changes. 

### 3.	IDs vs Full Objects
This is because returning only IDs saves bandwidth on the network and helps in enchancing the performance of networks, particularly with large datasets. But sending back entire objects will give more information and minimize further requests by the client.  The decision is based on the application, between efficiency and usability. 

### 4.	DELETE Idempotency
DELETE operation is idempotent since when one makes the same request several times to delete the same resource the result would be the same. After deleting a room, further DELETE requests do not alter the system and normally respond with a not found response. This guarantees predictive behaviour. 

### 5.	Wrong Content Type
The @Consumes (MediaType.ApplicationJSON) annotation can be used to make sure that the API accepts only JSON input. When a client transmits information in another format like text/plain or application/xml, JAX-RS will be unable to handle the request, and an error of HTTP 415 (Unsupported Media Type) is issued.

### 6.	QueryParam vs Path
Filtering with query parameters is more applicable as it allows flexible and optional query parameters without changing the URL format. Dynamic filtering can be used e.g. /sensors?type=Temperature. Conversely having filters in the path makes it less flexible and more difficult to extend the API. 

### 7.	Sub resource Locator
Sub-Resource Locator pattern enhances modularity by assigning the nested resource processing to different classes. Logic is not found in one class controlling all the endpoints but is found in specialized resources. This makes it less complex, more readable and the system is easier to maintain and extend.

### 8.	Side Effect
The system updates the currentValue of the sensor when a new sensor reading is added. This maintains the consistency between the historical information and the current value of the sensor and does not give outdated or wrong information.

### 9.	Using HTTP 422 vs 404 for Missing References
HTTP 422 is more appropriate because the request is syntactically correct but contains invalid data. The JSON structure is valid, but the referenced resource does not exist. A 404 is used when the endpoint itself is not found.

### 10.	  Security (Stack Trace)
Stack traces may contain sensitive data, including the names of the classes, file names and architecture. This information can be used by attackers to detect vulnerabilities and infiltrate the system. Thus, generic error messages enhance security. 

### 11.	  Logging Filters 
Logging logic is concentrated in a single place and does not need duplication with multiple resource methods using JAX-RS filters. It guarantees uniformity in logic entries of all requests and responses and isolates business logic and business cross cutting concerns. 
