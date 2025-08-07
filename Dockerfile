# Build
FROM gradle:9.0.0-jdk-lts-and-current-alpine AS build
WORKDIR /app
COPY app/build.gradle .
COPY app/src /app/src
RUN gradle --no-daemon clean build

# Deploy
FROM eclipse-temurin:21-jre-alpine
RUN addgroup -S springdocker
RUN adduser -S springdocker -G springdocker
USER springdocker:springdocker
WORKDIR /app
COPY --from=build /app/build/libs/spring-service-skeleton.jar .
EXPOSE 8080
ENTRYPOINT ["java","-jar", "spring-service-skeleton.jar"]
# provide environment variables in docker-compose
