FROM --platform=linux/amd64 openjdk:8-jdk-alpine
VOLUME /tmp
COPY target/EmployeeManagement-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java","-jar","/app.jar"]