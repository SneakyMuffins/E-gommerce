# Use the official Maven image as the build environment
FROM maven:3.8.7-eclipse-temurin-19 AS builder

# Set the working directory in the container
WORKDIR /app

# Copy the project files to the container
COPY pom.xml .
COPY src ./src

# Build the application using Maven
RUN mvn clean package -DskipTests

# Use the official OpenJDK image as the base image for the runtime environment
FROM openjdk:19

# Set the working directory in the container
WORKDIR /app

# Copy the compiled application from the builder stage
COPY --from=builder /app/target/demo-0.0.1-SNAPSHOT.jar ./app.jar

# Expose the port on which your Spring Boot app runs
EXPOSE 8080

# Run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
