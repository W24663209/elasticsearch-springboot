#!/usr/bin/env bash
mvn clean install -Dmaven.test.skip
FILE_NAME=$(pwd)/target/elasticsearch-0.0.1-SNAPSHOT.jar
scp  $FILE_NAME  ecs-user@8.219.104.130:/home/ecs-user/elasticsearch-docker/api