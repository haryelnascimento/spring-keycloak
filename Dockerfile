FROM openjdk:21

WORKDIR /app

ARG JAR_FILE='imob-app-0.0.1-SNAPSHOT.jar'

COPY ./target/${JAR_FILE} /app/api.jar

EXPOSE 8080

CMD ["java", "-jar", "api.jar"]