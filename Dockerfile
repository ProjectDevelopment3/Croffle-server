FROM openjdk:11
ARG JAR_FILE=build/libs/croffle-0.1.1-SNAPSHOT.jar
COPY ${JAR_FILE} app.jar
EXPOSE 8080
ENTRYPOINT ["java","-jar","/app.jar"]
