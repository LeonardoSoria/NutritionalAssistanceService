# Use Gradle with OpenJDK 17 as the builder
FROM gradle:8.11.1-jdk17 AS builder

WORKDIR /opt/app

# Copy Gradle wrapper and dependencies first for caching
COPY gradle gradle
COPY gradlew .
COPY build.gradle .
COPY settings.gradle .

# Run Gradle build to download dependencies
RUN ./gradlew build --no-daemon --stacktrace --warning-mode all || true

# Copy the rest of the project
COPY . .

# Build the project and skip tests
RUN ./gradlew bootJar -x test --no-daemon

# Use a smaller JDK base image for runtime
FROM openjdk:17-slim

WORKDIR /opt/app

# Copy the built JAR from the builder stage
COPY --from=builder /opt/app/webApi/build/libs/*.jar /opt/app/app.jar

EXPOSE 8080

ENTRYPOINT ["sh", "-c", "java -jar /opt/app/app.jar"]
