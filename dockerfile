# Etapa 1: Build con Maven
FROM maven:3.8.5-openjdk-17 AS build
WORKDIR /app
COPY . .
WORKDIR /app/hexagonal
RUN mvn clean package -DskipTests

# Etapa 2: Imagen final
FROM openjdk:17
WORKDIR /app
COPY --from=build /app/hexagonal/target/*.jar app.jar
ENTRYPOINT ["java", "-jar", "app.jar"]
