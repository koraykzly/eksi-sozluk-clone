FROM gradle:9.4.1-jdk25-alpine AS build
WORKDIR /app

COPY gradlew build.gradle settings.gradle ./
COPY gradle ./gradle
RUN ./gradlew dependencies --no-daemon

COPY src ./src
RUN ./gradlew bootJar --no-daemon

FROM eclipse-temurin:25-jre-alpine
WORKDIR /app

ENV SPRING_PROFILES_ACTIVE=docker

COPY --from=build /app/build/libs/*.jar app.jar
COPY uploads/profile-images/default-profile-picture-light.jpg uploads/profile-images/default-profile-picture-light.jpg

EXPOSE 8080

ENTRYPOINT ["java", "-jar", "app.jar"]
