# microservices
This is a basic Spring Boot microservices application, it's broken down to for microservices per se, each one can be using the 'mvn clean install' command which builds a fat uber jar where each on can be executed using the 'java -jar XX.jar'

# eureka-server
This server is the Service Discovery server based on Netflix's design, basically each microservice registers here for service discovery, of all the other micrservice this one need to be executed first

# zuul-server
Again another Netflix development, this microservice act as a reverse proxy, the idea behind using Zuul is to meet the requirement of a client sending a request to a server (Zuul proxy) which fowwards the request to the designated microservice (auth-service or rest-operation more on these later) & returns the result back through the Zuul proxy to the client. Also we can take advanate of Ribbon load balancing feature of Zuul in order to scale out horizonally a particular microservice if required (more on this later)

# auth-service
This microservice provides authentication & gives access to the required resources which grants access using the well known JWT token based authorization, it's neccessary to authenticate to this serivce first then use the corresponding JWT token in each subsequent request for each service we need to access (in this example it is the rest-operartion service)

# rest-operation 
This is a basic rest operation that currently provides two operations (add & multiply) using the get request parameters (x & y) in the URI. As both operations do not store any state & have no side effects we can use the GET HTTP verb to access both operations, this in effect means the the HTTP GET call is idempotent & also because no state is stored on this microservice they are inherently thread safe.

# General
The first microservice to launch is the serivce discovery : eureka-server (java -jar target/eureka-server-0.0.1-SNAPSHOT.jar), this will create the eureka server, it uses the default port of 8761 so after successfully executing you can visit the eureka dashboard at this URL http://localhost:8761/ , initially it will show no services registered we will get to this bit shortly, below is a screenshow of the initial dashboard
![Alt text](/eureka-initial.png?raw=true "Eureka Initial Dashboard")

After successful eurek-server launch, we can lauanch the microservices in the below order (note after each of the below successful launches they will each self register with the above mentioned eureka-server)
1. zuul-server : 'java -jar target/zuul-server-0.0.1-SNAPSHOT.jar'
2. auth-service : 'java -jar target/auth-service-0.0.1-SNAPSHOT.jar'
3. rest-operation : 'java -jar target/rest-operation-0.0.1-SNAPSHOT.jar'
    


