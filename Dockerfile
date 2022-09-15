FROM openjdk:17-alpine
MAINTAINER maciej.krasowski@radbrackets.com
COPY target/spring-quote-service.jar spring-quote-service.jar
ENTRYPOINT ["java", "-jar", "/spring-quote-service.jar"]