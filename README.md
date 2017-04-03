# spring-boot-oauth2-password-flow

It is Spring Boot application, that contains all necessary configuration to be able to try oauth2 authorization.
It uses JWT token key.

## Try it

1. mvnw spring-boot:run
2. curl -X POST -vu client:secret http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=admin&username=username&grant_type=password&scope=read%20write&client_secret=secret&client_id=client"
3. add "Authorization" header, with Bearer <token>

## Technology Stack

Java 8
Spring boot 1.5.2