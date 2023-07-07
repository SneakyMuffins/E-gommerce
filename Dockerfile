# Start with a base image that includes Java 19
FROM adoptopenjdk:19-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the pom.xml file to the container
COPY pom.xml .

# Copy the source code to the container
COPY src ./src

# Build the application with Maven
RUN ./mvnw clean package

# Set the entrypoint command to run the application
ENTRYPOINT ["java", "-jar", "target/demo-0.0.1-SNAPSHOT.jar"]
