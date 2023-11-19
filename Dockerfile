# Use a base image with Java (choose the appropriate version)
FROM openjdk:11

# Set the working directory in the container
WORKDIR /app

# Copy the Spring Boot application JAR file into the container
COPY target/demo.jar /app/demo.jar

# Copy the application.properties file into the container
COPY src/main/resources/application.properties /app/application.properties

# Expose the port that your Spring Boot application uses
EXPOSE 8091

# Command to run the Spring Boot application when the container starts
CMD ["java", "-jar", "demo.jar"]
