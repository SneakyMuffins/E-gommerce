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

# Install MySQL and dependencies
RUN apt-get update && \
    apt-get install -y mysql-server && \
    apt-get clean && \
    rm -rf /var/lib/apt/lists/*

# Copy the MySQL configuration file
COPY mysql.cnf /etc/mysql/mysql.cnf

# Initialize and configure MySQL
RUN mkdir /var/run/mysqld && \
    chown -R mysql:mysql /var/run/mysqld && \
    chmod 777 /var/run/mysqld && \
    service mysql start && \
    mysql -e "CREATE DATABASE egommerce;" && \
    mysql -e "CREATE USER 'misho'@'localhost' IDENTIFIED BY '';" && \
    mysql -e "GRANT ALL PRIVILEGES ON egommerce.* TO 'misho'@'localhost';"

# Set the environment variables for MySQL
ENV SPRING_DATASOURCE_URL=jdbc:mysql://localhost:3306/egommerce
ENV SPRING_DATASOURCE_USERNAME=misho
ENV SPRING_DATASOURCE_PASSWORD=
ENV SPRING_DATASOURCE_DRIVER_CLASS_NAME=com.mysql.cj.jdbc.Driver
ENV SPRING_JPA_GENERATE_DDL=true
ENV SPRING_JPA_HIBERNATE_DDL_AUTO=update
ENV SPRING_JPA_HIBERNATE_USE_NEW_ID_GENERATOR_MAPPINGS=false

# Run the Spring Boot application
CMD ["java", "-jar", "app.jar"]
