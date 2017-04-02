# spring-boot-oauth2-jwt

IN PROGRESS !!!

1. mvnw spring-boot:run
2. curl -X POST -vu client:secret http://localhost:8080/oauth/token -H "Accept: application/json" -d "password=admin&username=username&grant_type=password&scope=read%20write&client_secret=secret&client_id=client"
3. add "Authorization" header, with Bearer <token>
