FROM openjdk:11-jre-slim-bullseye
RUN adduser --system --group springdocker
USER springdocker:springdocker
ARG JAR_FILE=app/build/libs/spring-service-skeleton.jar
COPY ${JAR_FILE} spring-service-skeleton.jar
ENTRYPOINT ["java","-jar", \
#"-DTZ=America/Denver", \
#"-DVAR1=some_var", \
#"-DVAR2=another_var", \
"/spring-service-skeleton.jar"]
# ENV variables to add in docker-compose.yml
