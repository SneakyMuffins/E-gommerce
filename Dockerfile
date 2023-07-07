# Use a base image with Ubuntu and OpenJDK 19
FROM ubuntu:latest AS build

# Use the official MySQL 8.0 image
FROM mysql:8.0

# Set the root password (optional)
ENV MYSQL_ROOT_PASSWORD=root

# Create a user named "misho" with no password
ENV MYSQL_USER=misho
ENV MYSQL_PASSWORD=

# Expose the MySQL port
EXPOSE 3306

# Install OpenJDK 19
RUN apt-get update && \
    apt-get install -y openjdk-19-jdk

# Install Maven
RUN apt-get install -y maven

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .

# Copy the source code to the container
COPY src ./src

# Build the application with Maven
RUN mvn clean package -DskipTests

# Use a separate stage for the runtime image
FROM openjdk:19-jdk-slim

# Set the working directory in the container
WORKDIR /app

# Copy the built JAR file from the build stage to the runtime stage
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the desired port
EXPOSE 8080

# Set the entrypoint command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]
