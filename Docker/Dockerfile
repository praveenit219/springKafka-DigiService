#FROM openjdk:8-jdk-alpine
FROM anapsix/alpine-java:jre8 
LABEL maintainer="praveen.tirunamali@outlook.com"
EXPOSE 10185
VOLUME /tmp
ARG JAR_FILE
RUN set -ex \
  && apk update \
  && apk upgrade
RUN mkdir /digiservices
COPY ${JAR_FILE} /digiservices/pheonix-digitalservice.jar
WORKDIR /digiservices
CMD ["java", "-jar", "-Djava.security.egd=file:/dev/./urandom", "-Dspring.profiles.active=docker", "pheonix-digitalservice-v1.jar"]