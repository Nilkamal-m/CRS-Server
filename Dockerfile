FROM openjdk:8-alpine
ADD target/ComplaintRedressalSystem-0.0.1-SNAPSHOT.jar ComplaintRedressalSystem-0.0.1-SNAPSHOT.jar
ENTRYPOINT ["java","-jar","ComplaintRedressalSystem-0.0.1-SNAPSHOT.jar"]