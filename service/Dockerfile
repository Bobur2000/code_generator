# Use the official OpenJDK image as a base
FROM openjdk:17-jdk-alpine

# Set the working directory in the Docker image
WORKDIR /app

# Copy the Maven or Gradle build file and source code to the Docker image
COPY target/service-0.0.1-SNAPSHOT.jar app.jar

# Expose the port that your Spring Boot application runs on
EXPOSE 8081

# Command to run the Spring Boot application
ENTRYPOINT ["java", "-jar", "app.jar"]
