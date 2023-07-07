# Use the Maven base image
FROM maven:latest AS build

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

# Install MySQL client tools
RUN apt-get update && \
    apt-get install -y default-mysql-client

# Copy the built JAR file from the build stage to the runtime stage
COPY --from=build /app/target/demo-0.0.1-SNAPSHOT.jar app.jar

# Expose the desired port
EXPOSE 8080

# Wait for the MySQL database to be ready
CMD ["sh", "-c", "until nc -z mysql 3306; do sleep 1; done"]

# Set the entrypoint command to run the application
ENTRYPOINT ["java", "-jar", "app.jar"]

# Set environment variables for database configuration
ENV SPRING_DATASOURCE_URL jdbc:mysql://mysql:3306/egommerce?createDatabaseIfNotExist=true
ENV SPRING_DATASOURCE_USERNAME misho
ENV SPRING_DATASOURCE_PASSWORD yourpassword
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME com.mysql.cj.jdbc.Driver
ENV SPRING_JPA_GENERATE_DDL true
ENV SPRING_JPA_HIBERNATE_DDL_AUTO update
ENV APP_JWT_SECRET miwAF3qDRZnzz27iP4rXQVq0BqT45Et0uEjceNlGvC8=
ENV APP_JWT_EXPIRATION_IN_MS 259200000
ENV APP_PASS_SALT $2a$10$f5GWMWLgnVFbM/DvdkxQ1u
ENV GRAPHQL_SERVLET_MAPPING /graphql
ENV SPRING_GRAPHQL_GRAPHIQL_ENABLED true
