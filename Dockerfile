# Use a base image with Java 17
FROM eclipse-temurin:17-jdk-alpine

# Set the working directory inside the container
WORKDIR /app

# Copy the JAR file into the container
COPY target/kotlin-spring-crypto-webclient-0.0.1-SNAPSHOT.jar /app/kotlin-spring-crypto.jar

# Expose the port your application runs on
EXPOSE 8080

# Define the command to run your application
CMD ["java", "-jar", "kotlin-spring-crypto.jar"]
