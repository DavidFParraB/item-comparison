# Use an official Java 21 runtime as a base image
FROM eclipse-temurin:21-jre

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
# Replace 'app.jar' with the actual name of your JAR file
COPY target/app-1.0.0.jar app.jar

# Expose the port the application runs on (default for Spring Boot is 8080)
EXPOSE 8080

# Define the command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
