#-----------------
# BUILD STAGE
#-----------------

FROM gradle:8-jdk22-alpine AS builder

COPY --chown=gradle:gradle . /home/gradle/src

WORKDIR /home/gradle/src

RUN gradle build -x test

#-----------------
# PACKAGE STAGE
#-----------------

FROM openjdk:22-jdk

RUN mkdir /app

COPY --from=builder /home/gradle/src/build/libs/*.jar /app/api-gateway.jar

EXPOSE 8084

ENTRYPOINT ["java", "-jar", "/app/api-gateway.jar"]
