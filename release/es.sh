#!/usr/bin/env bash
mvn clean install -Dmaven.test.skip
FILE_NAME=/Users/mac01/IdeaProjects/elasticsearch-springboot/target/elasticsearch-0.0.1-SNAPSHOT.jar
scp  $FILE_NAME  ubuntu@100.84.20.133:/home/ubuntu/elasticsearch-docker/api