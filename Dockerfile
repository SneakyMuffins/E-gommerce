# Use a base image with the desired JDK version
FROM openjdk:19-jdk-slim AS build

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .

# Copy the source code to the container
COPY src ./src

# Build the application with Maven
RUN apt-get update && \
    apt-get install -y maven && \
    mvn clean package

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
