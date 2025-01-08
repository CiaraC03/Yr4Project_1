# Use an official jdk as a parent
FROM  openjdk:17-jdk

# Set the working directory in the container
WORKDIR /Yr4Project_1

# Copy the built application JAR file from your local machine to the container
COPY target/*.jar Yr4Project_1.jar

# Mkae port 8080 available to the world outside this container
EXPOSE 8080

# run the jar file
ENTRYPOINT ["java", "-jar", "Yr4Project_1.jar"]