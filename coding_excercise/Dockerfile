FROM openjdk:8-alpine

# update alpine and install bash
RUN apk update && apk add bash

RUN mkdir -p ~/coding_excercise/app
ENV PROJECT_HOME ~/coding_excercise/app

COPY target/coding_excercise-0.0.1-SNAPSHOT.jar coding_excercise-0.0.1-SNAPSHOT.jar

WORKDIR $PROJECT_HOME

ENTRYPOINT ["java", "-Dspring.data.mongodb.uri=mongodb://mongodb/urlinfo","-Djava.security.egd=file:/dev/./urandom","-jar","/coding_excercise-0.0.1-SNAPSHOT.jar"]