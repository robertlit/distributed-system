FROM gradle:latest AS builder

WORKDIR /app
COPY build.gradle ./
COPY settings.gradle ./
COPY src ./src/

RUN gradle bootJar

FROM eclipse-temurin:17-jdk-alpine

COPY --from=builder /app/build/libs/user-service-*.jar /user-service.jar
CMD ["java","-jar","/user-service.jar"]
