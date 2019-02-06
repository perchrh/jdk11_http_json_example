Example of JDK11 Http Client + gradle + jersey + jackson + jetty, with Junit 5

In this example there is a JAX-RS HTTP/JSON-based service for registering new customers. 
A number of steps are carried out to register a new customer.

As an entry point, read the HTTP-API declared in CustomerOnboardingResource. 
Then follow the flow down to the CustomerOnboardingService. 

Make sure to check out the test in CustomerOnboardingResourceTest, 
and the JDK11 Http Client usage in ApiClient. 

Other classes of note are TestServer - an embedded Jetty server, and 
Application - the webapp. 

Some uninteresting parts are glossed over. A TODO may be seen next to these parts. 
Data objects are mapped from the API of the frontend-layer to the API of the backend. 

To compile from the command line, use
$  ./gradlew clean build 

Note that IntelliJ doesn't automatically set the project SDK to JDK 11. 
Make sure to do that in module settings - project.