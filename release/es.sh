#!/usr/bin/env bash
FILE_NAME=/Users/weizongtang/IdeaProjects/elasticsearch-springboot/target/elasticsearch-0.0.1-SNAPSHOT.jar
scp -i ~/.ssh/website.pem $FILE_NAME  ubuntu@35.154.48.78:/home/ubuntu/elasticsearch-docker/api