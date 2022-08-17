FROM adoptopenjdk/openjdk15
ARG JAR_FILE=build/libs/CNU-SAM-BE-0.0.1-SNAPSHOT.jar
COPY ${JAR_FILE} /cnusam.jar
ENTRYPOINT ["java","-Dspring.profiles.active=default", "-jar", "/cnusam.jar"]