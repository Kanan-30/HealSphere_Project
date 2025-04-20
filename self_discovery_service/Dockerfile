# Stage 1: Build the application using Maven
FROM maven:3.9.3-eclipse-temurin-17 AS build
WORKDIR /app

# Copy Maven build files and source code
COPY pom.xml .
COPY src ./src

# Build the jar file (skip tests if desired)
RUN mvn clean package -DskipTests

# Stage 2: Use a lightweight JDK image to run the application
FROM openjdk:17-jdk-slim
WORKDIR /app

# Copy the built jar from the previous stage
COPY --from=build /app/target/*.jar app.jar

# Expose the application port
EXPOSE 9091

# Run the app
CMD ["java", "-jar", "app.jar"]

