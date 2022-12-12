FROM eclipse-temurin:17-jre-alpine
RUN adduser --system --group springdocker
USER springdocker:springdocker
ARG JAR_FILE=app/build/libs/spring-service-skeleton.jar
COPY ${JAR_FILE} spring-service-skeleton.jar
ENTRYPOINT ["java","-jar", \
#"-DPORT=8080", \
#"-DSPRING_PROFILES_ACTIVE=docker", \
#"-DTZ=America/Denver", \
#"-DVAR1=some_var", \
#"-DVAR2=another_var", \
"/spring-service-skeleton.jar"]
# ENV variables to add in docker-compose.yml
