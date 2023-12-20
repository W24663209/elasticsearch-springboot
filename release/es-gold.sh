#!/usr/bin/env bash
mvn clean install -Dmaven.test.skip
FILE_NAME=$(pwd)/target/elasticsearch-0.0.1-SNAPSHOT.jar
scp $FILE_NAME ecs-user@100.127.123.156:/home/ecs-user/mongodb/api