FROM openjdk:8
ADD target/spring-boot-corona-rest-api.jar spring-boot-corona-rest-api.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "spring-boot-corona-rest-api.jar"]
