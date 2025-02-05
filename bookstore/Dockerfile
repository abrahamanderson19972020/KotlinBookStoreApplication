# Use Amazon Corretto JDK 17 with Alpine
FROM amazoncorretto:21-alpine-jdk

# Set the working directory inside the container
WORKDIR /app

# Copy the project files into the container
COPY . .

# Build the application using Maven
RUN ./mvnw clean package -DskipTests

# Expose port 8080
EXPOSE 8080

# Run the application
CMD ["java", "-jar", "./target/bookstore-0.0.1-SNAPSHOT.jar"]
