# spring-boot-oauth2-password-flow

It is a Spring Boot application, that contains all necessary configurations to be able to try oauth2 authorization (password flow).
It uses JWT token key for the authorization.

There is a **hsql** embedded database in the application by default, and it contains two default users (they are uploaded by **resources/data.sql**) 

**admin / admin**
  - role: ROLE_ADMIN
  - privilege: PRIVILEGE_ADMIN_READ

**user / user**
  - role: ROLE_USER
  - privilege: PRIVILEGE_USER_READ

You can choose postgres instead of hsql, you have to change active spring profile to **postgres**.

## Try it

1. start the spring-boot app 
```
mvnw spring-boot:run 
```

OR 

```
mvnw spring-boot:run -Dspring.profiles.active=postgres
```

2. **get access_token** for

admin

```
curl -X POST -vu client:secret http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=admin&username=admin&grant_type=password&scope=read%20write&client_secret=secret&client_id=client"
```

user

```
curl -X POST -vu client:secret http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=user&username=user&grant_type=password&scope=read%20write&client_secret=secret&client_id=client"
```

It will return something like that:
```
{
"access_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJleHAiOjE0OTE0NjYxMTYsInVzZXJfbmFtZSI6InVzZXIiLCJhdXRob3JpdGllcyI6WyJQUklWSUxFR0VfVVNFUl9SRUFEIl0sImp0aSI6IjQ4MDVhZGQ3LWMzNTgtNDkzMC05ODkwLTEzNjNkNjJiZmQ0ZiIsImNsaWVudF9pZCI6ImNsaWVudCIsInNjb3BlIjpbInJlYWQiLCJ3cml0ZSJdfQ.7nMeIVuskhkmHXxX6CC6RZf9A_aXxsaoTXev6av4h64",
"token_type":"bearer",
"refresh_token":"eyJhbGciOiJIUzI1NiIsInR5cCI6IkpXVCJ9.eyJ1c2VyX25hbWUiOiJ1c2VyIiwic2NvcGUiOlsicmVhZCIsIndyaXRlIl0sImF0aSI6IjQ4MDVhZGQ3LWMzNTgtNDkzMC05ODkwLTEzNjNkNjJiZmQ0ZiIsImV4cCI6MTQ5NDAxNDkxNiwiYXV0aG9yaXRpZXMiOlsiUFJJVklMRUdFX1VTRVJfUkVBRCJdLCJqdGkiOiI2MmU0MTU3Yy1hOWNiLTRlYjMtODg1Ni0wMmJhOWI1ZjQ3OWQiLCJjbGllbnRfaWQiOiJjbGllbnQifQ.1fexTQcFC80VkqbDo5zJfCzq0vbPPvJVPp8Nr3CwH68",
"expires_in":43199,
"scope":"read write",
"jti":"4805add7-c358-4930-9890-1363d62bfd4f"}
```
From this, you need "access_token", you can check what it contains exactly via **jwt.io**.

3. add **Authorization** header, with Bearer <token>

```
curl -H "Authorization: bearer <token>" http://localhost:8080/user
```

OR

```
curl -H "Authorization: bearer <token>" http://localhost:8080/admin
```

Of course the http://localhost:8080/admin endpoint is accessible only by admin, and the http://localhost:8080/user is accessible only by user.
If you try to access the wrong endpoint with your user, then you will get an error:
{"error":"unauthorized","error_description":"Full authentication is required to access this resource"} 

> Recommendation: 
>    Use **Postman** instead of curl commands.

## FAQ

1. How to add new user

If you want to add a new user, then you have to add a new line in data.sql:

```  
INSERT INTO account (id, enabled, username, password) VALUES (3, true, <username>, <encryptedPassword>);
```

To generate encryptedPassword, you can use online bcrypt hash generator (e.g.: https://www.dailycred.com/article/bcrypt-calculator) or you can generate it with Spring Boot BCryptPasswordEncoder (https://docs.spring.io/spring-security/site/docs/current/apidocs/org/springframework/security/crypto/bcrypt/BCryptPasswordEncoder.html).

After that you have to insert new user into account_roles, based on what role you want to add to the user (role 1 = admin, role 2 = user).

```
INSERT INTO account_roles (account_id, roles_role_id) VALUES (3, 1);
```



## Technology Stack

* Java 8
* Spring boot 1.5.6