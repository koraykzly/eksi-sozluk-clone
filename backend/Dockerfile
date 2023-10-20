FROM openjdk:17-alpine

ENV spring_profiles_active=docker

WORKDIR /app

COPY ./src /app/src

COPY ./target/eksi-app.jar /app

CMD ["java", "-jar", "eksi-app.jar"]
