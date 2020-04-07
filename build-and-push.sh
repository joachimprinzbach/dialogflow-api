#!/bin/sh
VERSION=v0.1.0
DOCKER_ORG=joachimprinzbach
mvn clean install
docker build . -t $DOCKER_ORG/dialogflow-api:$VERSION
docker push $DOCKER_ORG/dialogflow-api:$VERSION