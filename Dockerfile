FROM openjdk:8
ADD target/*.jar loan-limit-check-service.jar
EXPOSE 8082
ENTRYPOINT ["java", "-jar", "loan-limit-check-service.jar"]