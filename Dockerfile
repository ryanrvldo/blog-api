FROM openjdk:11.0.8-jdk

COPY build/libs/blog-api-0.0.1-SNAPSHOT.jar /app/application.jar

CMD ["java", "-jar", "/app/application.jar" ]