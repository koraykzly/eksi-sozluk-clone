# İlk aşama: Derleme işlemi
FROM maven:3.8.4-openjdk-17 AS build

# Proje dizinine geç
WORKDIR /app

# Proje bağımlılıklarını yükle ve kaynak dosyaları kopyala
COPY pom.xml .
COPY src ./src

# Projeyi derle
RUN mvn clean package -DskipTests

# İkinci aşama: Derlenmiş projeyi çalıştırma
FROM openjdk:17-alpine

# Profil ayarlarını belirle
ENV spring_profiles_active=docker

# Çalışma dizinine geç
WORKDIR /app

# Derlenmiş JAR dosyasını bir önceki aşamadan kopyala
COPY --from=build /app/target/eksi-app.jar /app/eksi-app.jar

# Uygulamayı çalıştır
CMD ["java", "-jar", "eksi-app.jar"]
