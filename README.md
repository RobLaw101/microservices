# microservices
This is a basic Spring Boot microservices application, it's broken down to 4 microservices per se, each one can be built  into an fat uber jar (with all it's dependencies) using the maven command 'mvn clean install' & each can be executed using the 'java -jar XX.jar' command line op

# eureka-server
This server is the Service Discovery server based on Netflix's design, basically each microservice registers here for service discovery, of all the other microservices this one needs to be executed first

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

After successful registeration of the 3 above services the eureka dashboard should look as below image, note the 3 services resgistered on localhost with the respective ports (zuul-server --> 8081, auther-service --> 8082 & operation-service --> 8083)
![Alt text](/eureka-registered.png?raw=true "Eureka Registered Dashboard")

Now we get to the interesting part basically at this stage the system is up & running, to functional test I used the curl command to issues the GET operation (as explained below) but this can be easily tested using postman or similar UI tool for accessing a REST Web Service.
1. 'curl -v -L -H "Content-Type: application/json" -d '{"username":"rob","password":"12345"}' http://localhost:8081/auth'
    * note the above curl command is using the zull-server port on localhost:8081 with the auth endpoint
    * we populate a json body as input to the auth request usng the -d switch (username & password to authenticate)
    * with a 200 response code we will get the JWT token to use in subsequent request in the Authorization HTTP response header something like this (note use the curl -v switch to show the verbose output on the console 
        * Authorization: Bearer eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb2IiLCJhdXRob3JpdGllcyI6W10sImlhdCI6MTU3Njc0OTQyMCwiZXhwIjoxNTc2ODM1ODIwfQ.2Ud8b_co_c7Ysh2izuWVWqqb6Tc1Bk0jzIfgKeyGtgKuXrumAdHqGb9BrdSvhRtHAHH_J1aXHtize_eWduIbEQ
2. It's easier to populate the above JWT token in a TOKEN variable for ease of access in the subsequent curl calls like below
    * TOKEN='eyJhbGciOiJIUzUxMiJ9.eyJzdWIiOiJyb2IiLCJhdXRob3JpdGllcyI6W10sImlhdCI6MTU3Njc0OTQyMCwiZXhwIjoxNTc2ODM1ODIwfQ.2Ud8b_co_c7Ysh2izuWVWqqb6Tc1Bk0jzIfgKeyGtgKuXrumAdHqGb9BrdSvhRtHAHH_J1aXHtize_eWduIbEQ'
3. Now that we have authenticated with the above credentials we can use the above token vairable in the header our curl GET request as below
    * curl -X GET -H 'Accept: application/json' -H "Authorization: Bearer ${TOKEN}" 'http://localhost:8081/operation/add?x=10&y=45'
    * curl -X GET -H 'Accept: application/json' -H "Authorization: Bearer ${TOKEN}" 'http://localhost:8081/operation/multiply?x=10&y=45'
4. some things to notice about the above, we are not accessing the rest-operation resource (on port 8083) directly but going through the zuul-server resource (port 8081), if you try access on port 8083 or if you try access without a valid token you will get a HTTP 404 Not found or in case of no token a HTTP 401 Not Authorized & lastly if you use one of the non implemented REST verbs (POST, PUT, DELETE etc) you will get a HTTP 405 Method Not Implemented

#Improvement TODOs
Due to time constraints I could not do the below suggested improvements
1. Unit testing is fairly basic & limited to just the rest-operation service basic JUNIT/Hamcrest stuff like assertThat etc
". I begun integration tests but could could not complete on time using SpringBootTest annotation with injections etc, issue I had was loading a test.properties file to not enable the rest-operation to register with eureka (which caused an exception during test exuection), the code is competed out but included
2. improve logging, the bare bones infrastructure is in place using logback (logback-spring.xml) for each microservice with the lombok Slf4j annotation, logging is piped into a file (loback-spring.xml configures the path to pipe to, this is relevant to my machine so will need to be updated, ideally it should read from a property)
3. improve exception handling pretty much uses the basic Spring Basic stuff which is sufficient but could be more detailed etc
4. used a DB SQL or NoSQL to store the user credentails, currently only two users are hardcoded in memory as below in UserDetailsServiceImpl class in the auth-service microservice -->
   * rob/12345 
   * bob/12345

Lastly as the rest-operation service does not store state & has no side effects they are inherently thread safe, this combined with the more or less stateless REST web service allows the application to scale horizontal if needed, this could be composed via a number of Docker images & using AWS elasticity to spin up instances dynamically & register themselves with the eureka server to handle massive concurrent requests & similary reduce when load decreases.
    
        
        
    


    


