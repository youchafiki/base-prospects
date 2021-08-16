# Start with a base image containing Java runtime
FROM openjdk:8-jdk-alpine

# Add Maintainer Info
LABEL maintainer="youchafiki@gmail.com"

# Add a volume pointing to /tmp
VOLUME /tmp

# Make port 8082 available to the world outside this container
EXPOSE 8082

# The application's jar file
ARG JAR_FILE=target/base-prospects-0.0.1-SNAPSHOT.jar

# Add the application's jar to the container
ADD ${JAR_FILE} base-prospects.jar

COPY keycloak.crt keycloak.crt

RUN keytool -v -import -noprompt -alias server -file keycloak.crt -keystore $JAVA_HOME/jre/lib/security/cacerts -storepass changeit

# Run the jar file
ENTRYPOINT ["java","-Djava.security.egd=file:/dev/./urandom","-Dspring.profiles.active=prod","-jar","/base-prospects.jar"]
