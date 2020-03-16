#Author: Luciana Bontempo

FROM openjdk:8
ADD target/app-0.0.1-SNAPSHOT.jar app.jar
EXPOSE 8080
ENTRYPOINT ["java", "-jar", "app.jar"]