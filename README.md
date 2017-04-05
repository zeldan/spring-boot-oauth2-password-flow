# spring-boot-oauth2-password-flow

It is Spring Boot application, that contains all necessary configuration to be able to try oauth2 authorization.
It uses JWT token key for the authorization.

There is a **hsql** embedded database in the application, and it contains two default users (they are uploaded by **DefaultDataGenerator.java**) 

1. admin / admin
  - role: ROLE_ADMIN
  - privilege: PRIVILEGE_ADMIN_READ
2. user / user
  - role: ROLE_USER
  - privilege: PRIVILEGE_USER_READ


## Try it

1. mvnw spring-boot:run
2. **get access_token** for

admin

```
curl -X POST -vu client:secret http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=admin&username=admin&grant_type=password&scope=read%20write&client_secret=secret&client_id=client"
```

user

```
curl -X POST -vu client:secret http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=user&username=user&grant_type=password&scope=read%20write&client_secret=secret&client_id=client"
```

3. add **Authorization** header, with Bearer <token>

## Technology Stack

* Java 8
* Spring boot 1.5.2