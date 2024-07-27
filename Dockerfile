FROM amazoncorretto:21-alpine
LABEL authors="matthew"
ARG JAR_FILE=build/libs/*.jar
COPY ./build/libs/tool-rental-0.0.1-SNAPSHOT.jar  app.jar
ENTRYPOINT ["java", "-jar", "/app.jar"]
