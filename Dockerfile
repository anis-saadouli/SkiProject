# Use an official OpenJDK runtime as a parent image
FROM openjdk:17

# Set the working directory
WORKDIR /app

# Copy the JAR file into the container
COPY target/stationSki-1.0.jar stationSki-1.0.jar

# Expose the port your app runs on
EXPOSE 8087

# Run the application
ENTRYPOINT ["java", "-jar", "stationSki-1.0.jar"]