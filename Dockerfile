# Use a base image with JDK and Maven to build the application
FROM adoptopenjdk:17-jdk-hotspot AS build
WORKDIR /app

# Copy the Maven project
COPY pom.xml .
COPY src ./src

# Build the application
RUN ./mvnw clean package -DskipTests

# Create a lightweight container with the JAR file
FROM adoptopenjdk:17-jre-hotspot
WORKDIR /app
COPY --from=build /app/target/teacher-0.0.1-SNAPSHOT.jar ./app.jar

# Expose the port your app runs on
EXPOSE 8091

# Specify the command to run the application
CMD ["java", "-jar", "app.jar"]
