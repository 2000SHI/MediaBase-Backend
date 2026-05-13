# # Build stage
# FROM eclipse-temurin:17-jdk AS build
# WORKDIR /app
# COPY pom.xml mvnw ./
# COPY .mvn .mvn
# RUN chmod +x mvnw
# RUN ./mvnw dependency:go-offline
# COPY src src
# RUN ./mvnw clean package -DskipTests

# # Runtime stage
# FROM eclipse-temurin:17-jre
# COPY --from=build /app/target/media-base-0.0.1-SNAPSHOT.jar app.jar
# ENTRYPOINT ["java", "-jar", "app.jar"]

FROM eclipse-temurin:17-jdk
COPY target/media-base-0.0.1-SNAPSHOT.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]