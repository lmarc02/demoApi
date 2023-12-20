# Use an official OpenJDK runtime as a parent image
FROM openjdk:20-jdk

# Set the working directory in the container
WORKDIR /app

# Copy the JAR file into the container at /app
COPY ./demo-0.0.1-SNAPSHOT.jar /app/demo-0.0.1-SNAPSHOT.jar

# Expose port 8080
EXPOSE 8080

# Specify the command to run on container start
CMD ["java", "-jar", "demo-0.0.1-SNAPSHOT.jar"]
