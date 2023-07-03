FROM gradle:latest AS builder

WORKDIR /app
COPY build.gradle ./
COPY settings.gradle ./
COPY src ./src/

RUN gradle bootJar

FROM eclipse-temurin:17-jdk-alpine

COPY --from=builder /app/build/libs/recommendation-service-*.jar /recommendation-service.jar
CMD ["java","-jar","/recommendation-service.jar"]
